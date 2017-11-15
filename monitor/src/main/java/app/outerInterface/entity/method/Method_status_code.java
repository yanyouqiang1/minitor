package app.outerInterface.entity.method;

import app.database.domain.Monitor_method;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@Data
public class Method_status_code {
    long[] method_status_100;
    long[] method_status_200;
    long[] method_status_300;
    long[] method_status_400;
    long[] method_status_500;

    public void generate(List<Monitor_method> methods){
        int size = methods.size();
        method_status_100 = new long[size];
        method_status_200 = new long[size];
        method_status_300 = new long[size];
        method_status_400 = new long[size];
        method_status_500 = new long[size];
        for(int i=0;i<size;i++){
            method_status_100[i] = methods.get(size-i-1).getStatus_100();
            method_status_200[i] = methods.get(size-i-1).getStatus_200();
            method_status_300[i] = methods.get(size-i-1).getStatus_300();
            method_status_400[i] = methods.get(size-i-1).getStatus_400();
            method_status_500[i] = methods.get(size-i-1).getStatus_500();
        }
    }

}
