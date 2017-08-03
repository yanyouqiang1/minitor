package app.schedule.entity;

import app.schedule.entity.Service;
import lombok.Data;

import javax.naming.BinaryRefAddr;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
// 服务具体的 负载/轻松  方法
@Data
public class ConcreteData {
    private Service service;
    private List<BrieflyMethod> methodOverloadList;
    private List<BrieflyMethod> methodRelaxList;
    private List<BrieflyMethod> methodNormal;

    public ConcreteData(Service service) {
        methodOverloadList = new LinkedList<BrieflyMethod>();
        methodRelaxList = new LinkedList<BrieflyMethod>();
        methodNormal = new LinkedList<BrieflyMethod>();
        this.service = service;
    }

    @Data
    public static class BrieflyMethod{
        private Long id;
        private String name;
    }
}
