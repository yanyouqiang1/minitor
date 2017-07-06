package app.handleconfig.msgAnalyisis;

import app.handle.commonHandle.warehouse.AbstractOverallStatistics;
import entitylib.MsgEntity;

/**
 * Created by Administrator on 2017/7/6.
 */
public class OverallStatistics extends AbstractOverallStatistics{
    @Override
    public void update(MsgEntity msgEntity) {
        super.visitors++;
        String method = msgEntity.getMethod();
        if(method.equals("get")){
            super.method_get++;
        }else if(method.equals("post")){
            super.method_post++;
        }else if(method.equals("put")){
            super.method_put++;
        }else if(method.equals("delete")){
            super.method_delete++;
        }

        switch (msgEntity.getHttpStatus()){
            case 200:
                super.status_200++;
                break;
            case 300:
                super.status_300++;
                break;
            case 400:
                super.status_400++;
                break;
            case 500:
                super.status_500++;
                break;
        }

        int responsetime = msgEntity.getResposneTime();
        if(responsetime<super.response_min){
            super.response_min = responsetime;
        }
        if(responsetime>super.getResponse_max()){
            super.response_max = responsetime;
        }

        super.resposne_avg = (int) ((visitors-1)*resposne_avg/visitors);

        super.TPS = visitors/10;

    }

    @Override
    public String toString() {
        return "OverallStatistics{" +
                "visitors=" + visitors +
                ", method_get=" + method_get +
                ", method_post=" + method_post +
                ", method_put=" + method_put +
                ", method_delete=" + method_delete +
                ", status_200=" + status_200 +
                ", status_300=" + status_300 +
                ", status_400=" + status_400 +
                ", status_500=" + status_500 +
                ", response_min=" + response_min +
                ", response_max=" + response_max +
                ", resposne_avg=" + resposne_avg +
                ", TPS=" + TPS +
                '}';
    }
}
