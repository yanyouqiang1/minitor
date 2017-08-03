package app.webInterface.entity.overview;

import app.database.domain.Monitor_overall;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@Data
public class Overview_visitors {
    long[] overview_request_visitors,overview_response_visitors;


    public void generate(List<Monitor_overall> overalls){
        int size = overalls.size();
        overview_request_visitors = new long[size];
        overview_response_visitors = new long[size];
        for(int i=0;i<size;i++){
            overview_request_visitors[i] = overalls.get(i).getRequest_visitors();
        }
    }

}
