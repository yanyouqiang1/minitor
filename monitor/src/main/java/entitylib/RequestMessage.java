package entitylib;

/**
 * Created by Administrator on 2017/7/31.
 */
public class RequestMessage {
    private Long groupId; //属于哪个组
    private Long resourceId; //资源
    private Long methodId; //方法 post,get,..
    private Long serviceId; //通俗名称 如搜索服务

    public RequestMessage() {
    }

    public RequestMessage(Long groupId, Long resourceId, Long methodId, Long serviceId) {
        this.groupId = groupId;
        this.resourceId = resourceId;
        this.methodId = methodId;
        this.serviceId = serviceId;
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

}
