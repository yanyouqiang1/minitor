package app.Schedule;

/**
 * Created by Administrator on 2017/8/29.
 */
public interface ServiceSingleStrategyInter {
    public StrategySingleResult doStrategy(AbstractService service);

    //get name
    public String getStrategyName();

    //after strategy
    public void afterStrategy(AbstractService service);
}
