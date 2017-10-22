package app.Schedule.implementation;

import app.Schedule.AbstractService;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/8/29.
 */
@Component
public class Service extends AbstractService {

    @Override
    public void up() {
        System.out.println("服务：" + super.name + "容器增加");
        super.scale++;
    }

    @Override
    public void down() {
        if(scale>1) {
            System.out.println("服务：" + super.name + "容器减少");
            scale--;
        }
    }
}

