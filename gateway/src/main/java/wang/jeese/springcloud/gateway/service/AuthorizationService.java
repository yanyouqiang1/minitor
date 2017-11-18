package wang.jeese.springcloud.gateway.service;

import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wang.jeese.springcloud.gateway.model.GatewayData;
import wang.jeese.springcloud.gateway.model.Method;
import wang.jeese.springcloud.gateway.model.Resource;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PROXY_KEY;

/**
 * Created by jeese on 2017/6/7.
 */
@Service
public class AuthorizationService {

    private static Logger logger = LoggerFactory.getLogger(AuthorizationService.class);

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.header.access}")
    private String accessTokenHeader;

    public Boolean shouldFilter(RequestContext ctx){
        HttpServletRequest request = ctx.getRequest();
        if(ctx.getBoolean("nonAuthorizationFilter")){
            return false;
        }
        String rid = (String) ctx.get(PROXY_KEY);
        if(StringUtils.isEmpty(rid)){
            return false;
        }
        Resource resource = GatewayData.getInstance().getResourceMap().get(rid);
        if(null != resource){
            Method method = resource.getMethods().get(request.getMethod());
            if(null != method){
                return method.getAuthorization();
            }
        }
        return false;
    }

    public void authorize(RequestContext ctx){
        HttpServletRequest request = ctx.getRequest();
        String rid = (String) ctx.get(PROXY_KEY);
        if(StringUtils.isEmpty(rid)){
            return;
        }
        Resource resource = GatewayData.getInstance().getResourceMap().get(rid);
        if(null != resource){
            Method method = resource.getMethods().get(request.getMethod());
            if(null != method){
                try {
                    String authority = GatewayData.getInstance().getAuthorityMap().get(method.getAuthorityId());
                    if(StringUtils.isEmpty(authority)){
                        return;
                    }
                    if(null != ctx.get("authority")){
                        try {
                            Map<String, String> ctxAuthority = (Map<String, String>) ctx.get("authority");
                            if(null != ctxAuthority.get(authority)){
                                if(ctxAuthority.get(authority).equals(authority)){
                                    return;
                                }
                            }
                        }catch (ClassCastException e){
                            ctx.setSendZuulResponse(false);
                            ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
                            ctx.setResponseBody(HttpStatus.FORBIDDEN.getReasonPhrase());
                        }

                    }
                    ctx.setSendZuulResponse(false);
                    ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
                    ctx.setResponseBody(HttpStatus.FORBIDDEN.getReasonPhrase());
                }catch (NullPointerException e){

                }
            }
        }
    }

}
