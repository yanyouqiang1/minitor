package app.outerInterface.setting;


import lombok.Data;

/**
 * Created by Administrator on 2017/8/1.
 */
@Data
public class CommonReplyBuilder {
    public static CommonReply buildSuccessReply() {
        CommonReply commonReply = new CommonReply();
        commonReply.setStatus("ok");
        commonReply.setMsg("success");
        return commonReply;
    }

    public static CommonReply buildErrorReply(String errorMsg) {
        CommonReply commonReply = new CommonReply();
        commonReply.setStatus("error");
        commonReply.setMsg(errorMsg);
        return commonReply;
    }


}
