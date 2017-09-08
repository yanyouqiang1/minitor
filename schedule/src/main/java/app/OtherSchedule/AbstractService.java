package app.OtherSchedule;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/29.
 */
@Data
public abstract class AbstractService {
    protected String name;
    protected int scale;
    private List<ServiceOverallStrategyInter> serviceOverallStrategyInterList = new LinkedList<ServiceOverallStrategyInter>();
    private List<ServiceSingleStrategyInter> serviceSingleStrategyInterList = new LinkedList<ServiceSingleStrategyInter>();

    //up a service
    public abstract void up();

    //down a service
    public abstract void down();

    public void addSingleStrategy(ServiceOverallStrategyInter singleStrategy){
        serviceOverallStrategyInterList.add(singleStrategy);
    }
}
