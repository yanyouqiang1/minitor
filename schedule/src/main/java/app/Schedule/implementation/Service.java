package app.Schedule.implementation;

import app.Schedule.AbstractService;
import app.feignclient.targetAdapter.Adapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/8/29.
 */
@Component
public class Service extends AbstractService {

    @Autowired
    Adapter adapter;

    @Override
    public void up() {
        if(adapter.upService(super.name)){
            System.out.println("服务："+super.name+"容器增加成功");
            super.scale++;
        }else{
            System.out.println("服务："+super.name+"容器增加失败");
        }
    }

    @Override
    public void down() {
        if(adapter.downService(super.name)){
            System.out.println("服务："+super.name+"容器减少成功");
            super.scale++;
        }else{
            System.out.println("服务："+super.name+"容器减少失败");
        }

    }

}
