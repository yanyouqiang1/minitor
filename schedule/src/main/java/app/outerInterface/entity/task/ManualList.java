package app.outerInterface.entity.task;

import app.database.domain.Strategy_record;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */
@Data
public class ManualList {
    List<Strategy_record> manualist;

    public ManualList(List<Strategy_record> manualist) {
        this.manualist = manualist;
    }
}
