package app.outerInterface.entity.method;

import app.database.domain.Monitor_method;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@Data
public class Method_tps {
    long[] method_TPS;
    public void generate(List<Monitor_method> methods){
        int size = methods.size();
        method_TPS = new long[size];
        for(int i=0;i<size;i++){
            method_TPS[i] = methods.get(size-i-1).getTPS();
        }
    }

}
