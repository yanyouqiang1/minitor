package app.outerInterface.entity.task;

import app.database.domain.Strategy_record;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */
@Data
public class AutomaticList {
    List<Strategy_record> automaticList;

    public AutomaticList(List<Strategy_record> automaticList) {
        this.automaticList = automaticList;
    }
}
