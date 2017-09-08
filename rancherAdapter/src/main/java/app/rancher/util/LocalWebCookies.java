package app.rancher.util;

import app.globalUtil.HttpRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/3.
 */
public class LocalWebCookies {
    private static Map<String, String> webCookies = new HashMap<String, String>();
    private static Map<String, Date> expirations = new HashMap<String, Date>();//过期时间

    public static String getApiContent(String userName, String password, String url, String param) {
        Date expiration = expirations.get(url);
        if (expiration == null || expiration.getTime() < new Date().getTime()) {
            //不存在，或者过期
            String content = HttpRequest.sendGetwithAuthen(userName, password, url, "");
            webCookies.put(url, content);
            expirations.put(url, new Date(new Date().getTime() + 30 * 1000)); //设置30S超时
//            System.out.println(url+"###:"+expirations.get(url).getTime());
            return content;
        }
        return webCookies.get(url);
    }


    public static void clear() {
        webCookies.clear();
        expirations.clear();
    }
}
