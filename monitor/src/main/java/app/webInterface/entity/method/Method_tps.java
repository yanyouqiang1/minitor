package app.webInterface.entity.method;

import app.database.domain.Monitor_method;
import app.database.domain.Monitor_overall;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
public class Method_tps {
    long[] method_TPS;
    public void generate(List<Monitor_method> methods){
        int size = methods.size();
        method_TPS = new long[size];
        for(int i=0;i<size;i++){
            method_TPS[i] = methods.get(i).getTPS();
        }
    }

    public long[] getMethod_TPS() {
        return method_TPS;
    }

    public void setMethod_TPS(long[] method_TPS) {
        this.method_TPS = method_TPS;
    }
}
