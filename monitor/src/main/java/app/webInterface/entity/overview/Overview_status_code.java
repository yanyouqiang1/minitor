package app.webInterface.entity.overview;

import app.database.domain.Monitor_overall;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
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

    public long[] getOverview_status_100() {
        return Overview_status_100;
    }

    public void setOverview_status_100(long[] overview_status_100) {
        Overview_status_100 = overview_status_100;
    }

    public long[] getOverview_status_200() {
        return Overview_status_200;
    }

    public void setOverview_status_200(long[] overview_status_200) {
        Overview_status_200 = overview_status_200;
    }

    public long[] getOverview_status_300() {
        return Overview_status_300;
    }

    public void setOverview_status_300(long[] overview_status_300) {
        Overview_status_300 = overview_status_300;
    }

    public long[] getOverview_status_400() {
        return Overview_status_400;
    }

    public void setOverview_status_400(long[] overview_status_400) {
        Overview_status_400 = overview_status_400;
    }

    public long[] getOverview_status_500() {
        return Overview_status_500;
    }

    public void setOverview_status_500(long[] overview_status_500) {
        Overview_status_500 = overview_status_500;
    }
}
