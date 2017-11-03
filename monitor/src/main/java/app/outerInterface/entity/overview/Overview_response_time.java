package app.outerInterface.entity.overview;

import app.database.domain.Monitor_overall;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@Data
public class Overview_response_time {
    int[] overview_response_avg,overview_response_min,overview_response_max,overview_response_median,overview_response_90,overview_response_99;
    public void generate(List<Monitor_overall> overalls){
        int size = overalls.size();
        overview_response_avg = new int[size];
        overview_response_min = new int[size];
        overview_response_max = new int[size];
        overview_response_median = new int[size];
        overview_response_90 = new int[size];
        overview_response_99 = new int[size];
        for(int i=0;i<size;i++){
            overview_response_avg[i] = overalls.get(i).getResponse_avg();
            overview_response_min[i] = overalls.get(i).getResponse_min();
            overview_response_max[i] = overalls.get(i).getResponse_max();
            overview_response_median[i] = overalls.get(i).getResponse_max();
            overview_response_90[i] = overalls.get(i).getResponse_90();
            overview_response_99[i] = overalls.get(i).getResponse_99();
        }
    }

}
