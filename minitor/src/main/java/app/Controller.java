package app;

import app.handle.HandleInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
@RestController
public class Controller {
    @Autowired
    HandleInter handleInter;

    @RequestMapping("/")
    public String string(){
        return "welcome";
    }

    @RequestMapping("/account")
    public int getaccount(){
        return handleInter.getTotalAccess();
    }
}
