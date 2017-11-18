package wang.jeese.springcloud.gateway.model;

/**
 * Created by jeese on 2017/7/26.
 */
public class ResponseMessage {

    private Long groupId; //属于哪个组
    private Long resourceId; //资源
    private Long methodId; //方法 post,get,..
    private Long serviceId; //通俗名称 如搜索服务
    private Integer httpStatus; //状态码
    private Integer responseTime; //响应时间

    public ResponseMessage() {
    }

    public ResponseMessage(Long groupId, Long resourceId, Long methodId, Long serviceId, Integer httpStatus, Integer responseTime) {
        this.groupId = groupId;
        this.resourceId = resourceId;
        this.methodId = methodId;
        this.serviceId = serviceId;
        this.httpStatus = httpStatus;
        this.responseTime = responseTime;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getMethodId() {
        return methodId;
    }

    public void setMethodId(Long methodId) {
        this.methodId = methodId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
    }


}
