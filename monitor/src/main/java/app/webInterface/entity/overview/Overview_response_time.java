package app.webInterface.entity.overview;

import app.database.domain.Monitor_overall;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
public class Overview_response_time {
    int[] overview_response_time;
    public void generate(List<Monitor_overall> overalls){
        int size = overalls.size();
        overview_response_time = new int[size];
        for(int i=0;i<size;i++){
            overview_response_time[i] = overalls.get(i).getResponse_avg();
        }
    }

    public int[] getResponse_time() {
        return overview_response_time;
    }

    public void setResponse_time(int[] response_time) {
        this.overview_response_time = response_time;
    }
}
