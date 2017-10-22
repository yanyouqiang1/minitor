package app.handle.commonHandle.warehouse.statistics.gateway;

import lombok.Data;

/**
 * Created by Administrator on 2017/9/15.
 */
@Data
public class HandleResource {
    private Long id;
    private Long groupId;
    private Long serviceId;
    private String resourceName;
}
