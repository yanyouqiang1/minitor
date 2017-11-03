package app.outerInterface.entity.overview;

import app.database.domain.Monitor_overall;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@Data
public class Overview_success_rate {
    float[] overview_success_rate;
    public void generate(List<Monitor_overall> overalls){
        int size = overalls.size();
        overview_success_rate = new float[size];
        for(int i=0;i<size;i++){
            overview_success_rate[i] = overalls.get(i).getRate_status_200();
        }
    }

}
