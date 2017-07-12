package app.webInterface.entity.overview;

import app.database.domain.Monitor_overall;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
public class Overview_tps {
    long[] overview_TPS;
    public void generate(List<Monitor_overall> overalls){
        int size = overalls.size();
        overview_TPS = new long[size];
        for(int i=0;i<size;i++){
            overview_TPS[i] = overalls.get(i).getTPS();
        }
    }

    public long[] getTPS() {
        return overview_TPS;
    }

    public void setTPS(long[] TPS) {
        this.overview_TPS = TPS;
    }
}
