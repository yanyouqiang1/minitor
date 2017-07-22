package app;

import app.database.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/7/22.
 */
@RestController
public class Controller {
    @Autowired
    MethodService methodService;

    @RequestMapping("/test")
    public String test(){
        methodService.getServiceMethod("service1");
        return "ok";
    }
}
