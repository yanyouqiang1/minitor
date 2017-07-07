package app;

import app.database.dao.GroupRepository;
import app.database.dao.MethodRepository;
import app.database.dao.OverallRepository;
import app.database.domain.Baseoverall;
import app.database.domain.Mini_group;
import app.database.domain.Mini_method;
import app.database.domain.Mini_overall;
import app.database.service.KeeptoSave;
import app.handle.HandleInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
@RestController
public class Controller {
    @Autowired
    KeeptoSave keeptoSave;

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

    @RequestMapping("/current")
    Mini_overall wholecurrent(){
        long id = keeptoSave.getId();
        return overallRepository.findOne(id);
    }

    @RequestMapping("/history/{type}/{param}")
    List history(@PathVariable("type") String type, @PathVariable("param") String param){
        if(type.equals("method")){
            //param为resource名称
            String resouceName = param;
            return methodRepository.findTop10ByResourceOrderByIdDesc(resouceName);
        }else if(type.equals("group")){
            //param 为组名
            String groupName = param;
            return groupRepository.findTop10ByGroupNameOrderByIdDesc(groupName);
        }else{  //all
            //为数量
            int c = Integer.valueOf(param);
            long id= keeptoSave.getId();
            return overallRepository.getBetween(id-c,id);
        }
    }



}
