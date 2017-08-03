package app.webInterface.entity.overview;

import app.database.domain.Monitor_overall;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@Data
public class Overview_status_code {
    long[] Overview_status_100;
    long[] Overview_status_200;
    long[] Overview_status_300;
    long[] Overview_status_400;
    long[] Overview_status_500;

    public void generate(List<Monitor_overall> overalls){
        int size = overalls.size();
        Overview_status_100 = new long[size];
        Overview_status_200 = new long[size];
        Overview_status_300 = new long[size];
        Overview_status_400 = new long[size];
        Overview_status_500 = new long[size];
        for(int i=0;i<size;i++){
            Overview_status_100[i] = overalls.get(i).getStatus_100();
            Overview_status_200[i] = overalls.get(i).getStatus_200();
            Overview_status_300[i] = overalls.get(i).getStatus_300();
            Overview_status_400[i] = overalls.get(i).getStatus_400();
            Overview_status_500[i] = overalls.get(i).getStatus_500();
        }
    }

}
