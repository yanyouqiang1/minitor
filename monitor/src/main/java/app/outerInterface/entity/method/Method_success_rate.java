package app.outerInterface.entity.method;

import app.database.domain.Monitor_method;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@Data
public class Method_success_rate {
    float[] method_success_rate;
    public void generate(List<Monitor_method> methods){
        int size = methods.size();
        method_success_rate = new float[size];
        for(int i=0;i<size;i++){
            method_success_rate[i] = methods.get(i).getRate_status_200();
        }
    }

}
