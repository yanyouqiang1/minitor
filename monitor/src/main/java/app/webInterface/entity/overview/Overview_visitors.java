package app.webInterface.entity.overview;

import app.database.domain.Monitor_overall;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
public class Overview_visitors {
    long[] overview_visitors;


    public void generate(List<Monitor_overall> overalls){
        int size = overalls.size();
        overview_visitors = new long[size];
        for(int i=0;i<size;i++){
            overview_visitors[i] = overalls.get(i).getRequest_visitors();
        }
    }


    public long[] getVisitors() {
        return overview_visitors;
    }

    public void setVisitors(long[] visitors) {
        this.overview_visitors = visitors;
    }
}
