package app;

import app.database.dao.GroupRepository;
import app.database.dao.MethodRepository;
import app.database.dao.OverallRepository;
import app.database.domain.Mini_group;
import app.database.domain.Mini_method;
import app.database.domain.Mini_overall;
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

    @Autowired
    OverallRepository overallRepository;

    @Autowired
    MethodRepository methodRepository;

    @Autowired
    GroupRepository groupRepository;

    @RequestMapping("/overall")
    List<Mini_overall> getall(){
        return overallRepository.findall1();
    }
    @RequestMapping("/group")
    List<Mini_group> getgroup(){
        return groupRepository.findAll();
    }
    @RequestMapping("/method")
    List<Mini_method> getmethod(){
        return methodRepository.findAll();
    }


}
