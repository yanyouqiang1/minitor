import org.json.JSONArray;
import org.json.JSONObject;
import app.schedule.util.HttpRequest;
import app.schedule.util.JsonUtil;

import java.util.Iterator;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
public class test1 {
    public static void main(String[] args) throws Exception {
//        Object d = null;
//        String str = (String)d;
//        System.out.println(str);
//        String url = "http://172.18.218.252:8080/v1-webhooks/endpoint?key=zTAUvX07UY4PbmEZ3MHcGHGDLo4N1TRuDUxrFnww&projectId=1a5";
//        String result = HttpRequest.httpClientPost(url,null,"UTF8");
//        System.out.println(result);

        String url = "http://172.18.218.252:8080/v2-beta/projects/1a5/services/1s157";
        String result = HttpRequest.sendGetwithAuthen("91DDC5CF113157397E29", "GQ7iECDwtihYyMbgHZg3vUMn4yDQYR1CawB8xFDa", url, "");
        System.out.println(result);

        JSONObject jsonObject = new JSONObject(result);

        Object o = JsonUtil.getJson(result, "linkedServices");
        if (o instanceof String) {
            System.out.println(o.toString());
        } else if (o instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) o;

            for (int i = 0; i < jsonArray.length(); i++) {
                System.out.println(jsonArray.get(i));
            }
        }else if(o instanceof JSONObject){
            JSONObject jsonObject1 = (JSONObject) o;
            Iterator iterator = jsonObject1.keys();
            while (iterator.hasNext()) {
                String keys = (String) iterator.next();
                System.out.println(jsonObject1.getString(keys));
            }
        }
//        String str = jsonObject.getString("name");
//        System.out.println(str);
//        JSONArray jsonArray = jsonObject.getJSONArray("name");

//        for(int i=0;i<jsonArray.length();i++){
//            System.out.println(jsonArray.get(i));
//        }


    }
}
