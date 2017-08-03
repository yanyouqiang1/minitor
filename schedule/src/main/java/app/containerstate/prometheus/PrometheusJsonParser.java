package app.containerstate.prometheus;

import app.schedule.rancherImpl.entity.ResultData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by Administrator on 2017/7/29.
 */
public class PrometheusJsonParser {
    public static ResultData parser(String json) throws Exception {
        if (json == null || json == "") return null;
        ResultData resultData = new ResultData();
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject) parser.parse(json);
        String status= object.get("status").getAsString();
        if(!status.equals("success")){
            Exception exception = new Exception("Prometheus 返回状态错误"+status+",请检查你的相关配置");
            throw  exception;
        }
        resultData.setStatus(status);
        try {
            JsonObject data = object.getAsJsonObject("data");
            JsonArray results = data.getAsJsonArray("result");
            for (int i = 0; i < results.size(); i++) {
                JsonObject result = (JsonObject) results.get(i);
                JsonArray values = result.getAsJsonArray("values");
                for (int j = 0; j < values.size(); j++) {
                    ResultData.PrometheusPoint point = new ResultData.PrometheusPoint();
                    JsonArray value = (JsonArray) values.get(j);
                    point.setTime(value.get(0).getAsDouble());
                    point.setValue(value.get(1).getAsString());
                    resultData.getPoints().add(point);
                }
            }
        }catch (Exception e){
            Exception exception = new Exception("Prometheus json转化错误 ");
            throw  exception;
        }
        return resultData;
    }
}
