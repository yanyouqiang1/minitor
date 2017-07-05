package entitylib;

import org.apache.http.protocol.HTTP;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
public class MsgEntity {
    int httpStatus; //状态码
    String group; //属于哪个组
    String resouce; //资源
    String method; //方法 post,get,..
    String popularName; //通俗名称 如搜索服务
    String springcloudName; //spring cloud 服务名称
    int resposneTime; //响应时间

    public MsgEntity() {
    }

    public MsgEntity(int httpStatus, String group, String resouce, String method, String popularName, String springcloudName, int resposneTime) {
        this.httpStatus = httpStatus;
        this.group = group;
        this.resouce = resouce;
        this.method = method;
        this.popularName = popularName;
        this.springcloudName = springcloudName;
        this.resposneTime = resposneTime;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getResouce() {
        return resouce;
    }

    public void setResouce(String resouce) {
        this.resouce = resouce;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPopularName() {
        return popularName;
    }

    public void setPopularName(String popularName) {
        this.popularName = popularName;
    }

    public String getSpringcloudName() {
        return springcloudName;
    }

    public void setSpringcloudName(String springcloudName) {
        this.springcloudName = springcloudName;
    }

    public int getResposneTime() {
        return resposneTime;
    }

    public void setResposneTime(int resposneTime) {
        this.resposneTime = resposneTime;
    }
}
