package app.schedule.rancher.rancherUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
public class JsonUtil {

    public static Object getJson(String json,String queryName) throws JSONException {
        Object resultO=null;
        if(json.equals("")||json==null||queryName.equals("")||queryName==null){
            return null;
        }
        JSONObject jsonObject = new JSONObject(json);
        Iterator iterator = jsonObject.keys();
        while(iterator.hasNext()){
            String keys = iterator.next().toString();
            if(keys.equals(queryName)){
                resultO = jsonObject.get(keys);
                break;
            }
        }
        return resultO;
    }

    /***
     * json中查询某个字段
     * @param json
     * @param queryName
     * @return 返回List
     * @throws JSONException
     */
    public static List<String> getList(String json,String queryName) throws JSONException {
        List<String> list = new ArrayList<String>();
        Object o = JsonUtil.getJson(json, queryName);
        if (o instanceof String) {
            list.add(o.toString());
        } else if (o instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) o;

            for (int i = 0; i < jsonArray.length(); i++) {
//                System.out.println(jsonArray.get(i));
                list.add(jsonArray.get(i).toString());
            }
        }else if(o instanceof JSONObject){
            JSONObject jsonObject1 = (JSONObject) o;
            Iterator iterator = jsonObject1.keys();
            while (iterator.hasNext()) {
                String keys = (String) iterator.next();
                list.add(jsonObject1.getString(keys).toString());
//                System.out.println(jsonObject1.getString(keys));
            }
        }
        return list;
    }

}
