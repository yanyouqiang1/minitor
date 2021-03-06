package app.outerInterface.entity.method;

import app.database.domain.Monitor_method;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@Data
public class Method_response_time {
    int[] method_response_time;
    public void generate(List<Monitor_method> methods){
        int size = methods.size();
        method_response_time = new int[size];
        for(int i=0;i<size;i++){
            method_response_time[i] = methods.get(size-i-1).getResponse_avg();
        }
    }

}
