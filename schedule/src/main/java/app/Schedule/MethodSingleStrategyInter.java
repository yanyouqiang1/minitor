package app.Schedule;

/**
 * Created by Administrator on 2017/9/7.
 */
public interface MethodSingleStrategyInter {
    public StrategySingleResult doStrategy(AbstractMethod method, AbstractService abstractService);

    //get name
    public String getStrategyName();
}
