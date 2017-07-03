package app.msgentity;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
public class CommonMsgBuilder {
    public static CommonMsg successMsg(){
        return new CommonMsg("ok");
    }
    public static CommonMsg unknownErrorMsg(){
        return new CommonMsg("unknown error");
    }
    public static CommonMsg serverErrorMsg(){
        return new CommonMsg("server error");
    }
    public static CommonMsg requestErrorMsg(){
        return new CommonMsg("request param error");
    }
}
