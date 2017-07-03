package app;

import app.manage.ManageInter;
import app.msgentity.CommonMsg;
import app.msgentity.CommonMsgBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
@RestController
public class Controller {
    @Autowired
    ManageInter manageInter;

    @RequestMapping("/services")
    public List<String> getALLservice() {
        return manageInter.getAllServcie();
    }


    @RequestMapping("/toplogyMap")
    public int[][] toplogyMap() {
        return manageInter.getToplogyMap();
    }

    @RequestMapping(value = "/upService", method = RequestMethod.POST)
    public CommonMsg upService(@RequestParam(value = "serviceName", required = true) String serviceName) {
        if (serviceName.equals("")) {
            return CommonMsgBuilder.requestErrorMsg();
        }
        if (manageInter.upService(serviceName)) {
            return CommonMsgBuilder.successMsg();
        } else {
            return CommonMsgBuilder.unknownErrorMsg();
        }
    }

    @RequestMapping(value = "/downService", method = RequestMethod.POST)
    public CommonMsg downService(@RequestParam(value = "serviceName", required = true) String serviceName) {
        if (serviceName.equals("")) {
            return CommonMsgBuilder.requestErrorMsg();
        }
        if (manageInter.downService(serviceName)) {
            return CommonMsgBuilder.successMsg();
        } else {
            return CommonMsgBuilder.unknownErrorMsg();
        }
    }

    @RequestMapping("/")
    public CommonMsg welcome() {
        return CommonMsgBuilder.successMsg();
    }

}
