package entitylib;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
public class MsgEntity2 {
    int httpStatus; //状态码
    Long groupid; //属于哪个组
    Long resouceid; //资源
    Long methodid; //方法 post,get,..
    Long serviceid; //通俗名称 如搜索服务
    int resposneTime; //响应时间

    public MsgEntity2() {
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public Long getResouceid() {
        return resouceid;
    }

    public void setResouceid(Long resouceid) {
        this.resouceid = resouceid;
    }

    public Long getMethodid() {
        return methodid;
    }

    public void setMethodid(Long methodid) {
        this.methodid = methodid;
    }

    public Long getServiceid() {
        return serviceid;
    }

    public void setServiceid(Long serviceid) {
        this.serviceid = serviceid;
    }

    public int getResposneTime() {
        return resposneTime;
    }

    public void setResposneTime(int resposneTime) {
        this.resposneTime = resposneTime;
    }
}
