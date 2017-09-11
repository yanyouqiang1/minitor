package app.Schedule;

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
    private List<AbstractService> normalList;
    public StrategyOverallResult(){
        upList = new LinkedList<AbstractService>();
        downList = new LinkedList<AbstractService>();
        normalList = new LinkedList<AbstractService>();
    }
}
