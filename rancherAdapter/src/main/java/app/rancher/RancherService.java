package app.rancher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
@Data
public class RancherService {
    private String name;
    private String serviceId;
    private int scale;
    private List<String> containerNames = new LinkedList<String>();
    private List<RancherService> linkedServices = new LinkedList<RancherService>();

    @JsonIgnore
    RancherOS rancherOS;

    public RancherService(RancherOS rancherOS) {
        this.rancherOS = rancherOS;
    }

    public boolean upService() {
        if (rancherOS.scaleService(name, true)) {
            scale++;
            System.out.println("服务:" + name + " 容器增加");
            return true;
        }
        return false;

    }

    public boolean downService() {
        if (scale > 1 && rancherOS.scaleService(name, false)) {
            scale--;
            System.out.println("服务:" + name + " 容器减少");
            return true;
        }
        return false;

    }
}
