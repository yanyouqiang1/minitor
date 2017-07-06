package app.database.service;

import app.database.dao.OverallRepository;
import app.database.domain.Mini_group;
import app.database.domain.Mini_method;
import app.database.domain.Mini_overall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/6.
 */
@Component
public class KeeptoSave {
    @Autowired
    OverallRepository overallRepository;
    private static Mini_overall OVERALL;

    public Mini_overall getOverall() {
        return OVERALL;
    }

    public void setOverall(Mini_overall overall) {
        if(OVERALL!=null){
            overallRepository.save(OVERALL);
        }
        OVERALL = overall;
    }

    public void save(Mini_group mini_group){
        mini_group.setOverall(OVERALL);
        OVERALL.getGroups().add(mini_group);
    }
    public void save(Mini_method method){
        Set groups = OVERALL.getGroups();
        Iterator iterator = groups.iterator();
        while(iterator.hasNext()){
            Mini_group group = (Mini_group) iterator.next();
            if(group.getGroupName().equals(method.getGroupname())){
                group.getMethods().add(method);
                method.setGroup(group);
            }
        }
    }
}
