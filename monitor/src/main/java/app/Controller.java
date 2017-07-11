package app;

import app.database.dao.GroupRepository;
import app.database.dao.MethodRepository;
import app.database.dao.OverallRepository;
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

    @Autowired
    OverallRepository overallRepository;

    @Autowired
    MethodRepository methodRepository;

    @Autowired
    GroupRepository groupRepository;



}
