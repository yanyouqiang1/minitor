package app.rancher.util;

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

    public static Object getJson(JSONObject json, String queryName) throws JSONException {
        Object resultO = null;
        if (json == null || queryName.equals("") || queryName == null) {
            return null;
        }
        Iterator iterator = json.keys();
        while (iterator.hasNext()) {
            String keys = iterator.next().toString();
            if (keys.equals(queryName)) {
                resultO = json.get(keys);
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
    public static List<String> getList(String json, String queryName) throws JSONException {
        List<String> list = new ArrayList<String>();
        JSONObject jsonObject = new JSONObject(json);
        Object o = JsonUtil.getJson(jsonObject, queryName);
        if (o instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) o;

            for (int i = 0; i < jsonArray.length(); i++) {
//                System.out.println(jsonArray.get(i));
                list.add(jsonArray.get(i).toString());
            }
        } else if (o instanceof JSONObject) {
            JSONObject jsonObject1 = (JSONObject) o;
            Iterator iterator = jsonObject1.keys();
            while (iterator.hasNext()) {
                String keys = (String) iterator.next();
                list.add(jsonObject1.getString(keys).toString());
//                System.out.println(jsonObject1.getString(keys));
            }
        } else if(o!=null){
            if(!o.toString().equals("null"))
                list.add(o.toString());
        }
        return list;
    }

}
