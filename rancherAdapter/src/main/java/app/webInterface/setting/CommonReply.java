package app.webInterface.setting;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/3.
 */
@Data
public class CommonReply {
    private String status;
    private String msg;
    private Date time;

    public CommonReply() {
        time = new Date();
    }
}
