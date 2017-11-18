package wang.jeese.springcloud.gateway.service;

import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wang.jeese.springcloud.gateway.filter.AuthenticationFilter;
import wang.jeese.springcloud.gateway.model.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PROXY_KEY;

/**
 * Created by jeese on 2017/6/7.
 */
@Service
public class AuthenticationService {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.header.access}")
    private String accessTokenHeader;
    @Value("${account.header}")
    private String accountHeader;

    public Boolean shouldFilter(RequestContext ctx){
        HttpServletRequest request = ctx.getRequest();
        String rid = (String) ctx.get(PROXY_KEY);
        if(StringUtils.isEmpty(rid)){
            return true;
        }
        Resource resource = GatewayData.getInstance().getResourceMap().get(rid);
        if(null != resource){
            ctx.put("GroupId", resource.getGroupId());
            ctx.put("ResourceId", resource.getId());
            ctx.put("ServiceId", resource.getServiceId());
            Method method = resource.getMethods().get(request.getMethod());
            if(null != method){
                ctx.put("MethodId", method.getId());
                return method.getAuthentication();
            }
        }
        return true;
    }

    public void authenticate(RequestContext ctx){
        HttpServletRequest request = ctx.getRequest();
        String accessToken = request.getHeader(accessTokenHeader);
        if(StringUtils.isEmpty(accessToken)){
            try {
                Cookie[] cookies = request.getCookies();
                for(Cookie cookie: cookies){
                    if(cookie.getName().equals(accessTokenHeader)){
                        accessToken = cookie.getValue();
                        break;
                    }
                }
            }catch (NullPointerException e){
            }
        }
        if (StringUtils.isEmpty(accessToken) || !validateJwt(accessToken)){
            ctx.set("nonAuthorizationFilter", true);
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            ctx.setResponseBody(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }else{
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(accessToken).getBody();
            ctx.addZuulRequestHeader(accountHeader, claims.getId());
            Map<String, String> authorityMap = new HashMap<>();
            if(null == claims.get("aut")){
                ctx.set("authority", null);
                return;
            }
            try {
                List<String> authorityList = (List<String>) claims.get("aut");
                if(authorityList.isEmpty()){
                    ctx.set("authority", null);
                    return;
                }
                for(String str: authorityList){
                    authorityMap.put(str, str);
                }
                ctx.set("authority", authorityMap);
            }catch (ClassCastException e){

            }
        }


    }

    private Boolean validateJwt(String jwt){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
            //OK, we can trust this JWT
            return true;
        } catch (Exception e) {
            //don't trust the JWT!
            return false;
        }
    }

}
