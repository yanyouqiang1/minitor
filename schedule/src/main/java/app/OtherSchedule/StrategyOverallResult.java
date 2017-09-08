package app.OtherSchedule;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/7.
 */
@Data
public class StrategyOverallResult {
    private List<AbstractService> upList;
    private List<AbstractService> downList;
    public StrategyOverallResult(){
        upList = new LinkedList<AbstractService>();
        downList = new LinkedList<AbstractService>();
    }
}
