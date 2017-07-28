package entitylib;

import lombok.Data;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
@Data
public class MsgEntity {
    private Long groupId; //属于哪个组
    private Long resourceId; //资源
    private Long methodId; //方法 post,get,..
    private Long serviceId; //通俗名称 如搜索服务
    private Integer httpStatus; //状态码
    private Integer responseTime; //响应时间
    public MsgEntity() {
    }

}
