package app.webInterface.entity.method;

import app.database.domain.Monitor_method;
import app.database.domain.Monitor_overall;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
public class Method_response_time {
    int[] method_response_time;
    public void generate(List<Monitor_method> methods){
        int size = methods.size();
        method_response_time = new int[size];
        for(int i=0;i<size;i++){
            method_response_time[i] = methods.get(i).getResposne_avg();
        }
    }

    public int[] getMethod_response_time() {
        return method_response_time;
    }

    public void setMethod_response_time(int[] method_response_time) {
        this.method_response_time = method_response_time;
    }
}
