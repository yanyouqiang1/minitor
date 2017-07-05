package app;

import app.database.dao.UserRepository;
import app.database.domain.Person;
import app.database.domain.User;
import app.handle.HandleInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/getdata")
    User getall(){
        return userRepository.findOne(1L);
    }

    @RequestMapping("/all")
    List<User> all(){
        return userRepository.findAll();
    }
    @RequestMapping("/save")
    void save(){
        userRepository.save(new User("100",100));
    }
}
