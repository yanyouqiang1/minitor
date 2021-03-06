package app.outerInterface.entity.method;

import app.database.domain.Monitor_method;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@Data
public class Method_visitors {
    long[] method_visitors;
    public void generate(List<Monitor_method> methods){
        int size = methods.size();
        method_visitors = new long[size];
        for(int i=0;i<size;i++){
            method_visitors[i] = methods.get(size-i-1).getRequest_visitors();
        }
    }
}
