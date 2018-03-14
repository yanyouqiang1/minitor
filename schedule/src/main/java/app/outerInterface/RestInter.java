package app.outerInterface;

import app.database.domain.Strategy_timeWindow;
import app.database.domain.Strategy_timePeriod;
import app.database.domain.Strategy_visitorAverage;
import app.database.domain.Strategy_visitorLimit;
import app.outerInterface.entity.observation.*;
import app.outerInterface.entity.re.ParamTimePeriod;
import app.outerInterface.entity.re.ParamTimeWindow;
import app.outerInterface.entity.re.ParamVisitorLimit;
import app.outerInterface.entity.reply.CommonReply;
import app.outerInterface.entity.strategy.Overview;
import app.outerInterface.entity.task.AutomaticList;
import app.outerInterface.entity.task.ManualList;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */
public interface RestInter {
    /***
     *  所有服务响应时间
     * @return
     */
    @RequestMapping(value = "/observation/services", method = RequestMethod.GET)
    public RestServices getRestService();

    /***
     *  单个服务响应时间
     * @return
     */
    @RequestMapping(value = "/observation/service/{serviceName}/response")
    public RestServiceResponse getRestServiceResponse(@PathVariable(name = "serviceName", required = true) String serviceName);

    /***
     * 单个服务容器变化
     * @return
     */
    @RequestMapping(value = "/observation/service/{serviceName}/container")
    public RestServiceContainer getRestServiceContainer(@PathVariable(name = "serviceName", required = true) String serviceName);

    /***
     *  单个服务所有容器CPU使用情况
     * @return
     */
    public RestServiceContainerCpu getRestServiceContainerCpu(@PathVariable(name = "serviceName", required = true) String serviceName);

    /***
     * 单个服务所有容器内存使用情况
     * @return
     */

    public RestServiceContainerMemory getRestServiceContainerMemory(String serviceName);

    /***
     *  get autoamtic list
     * @return
     */
    @RequestMapping("/task/automaticList")
    public AutomaticList getAutomaticList(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size);


    /***
     *  get manual list
     * @return
     */
    @RequestMapping("/task/manualList")
    public ManualList getManualList(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size);

    /***
     *  set strategy time period
     * @param paramTimePeriod
     * @return
     */
    @RequestMapping(value = "/strategy/serviceTimePeriod/update", method = RequestMethod.PUT)
    public CommonReply updateStrategyTimePeriod(@RequestBody ParamTimePeriod paramTimePeriod);

    /***
     *  set strategy visitor limit
     * @param paramVisitorLimit
     * @return
     */
    @RequestMapping(value = "/strategy/serviceVisitorLimit/update", method = RequestMethod.PUT)
    public CommonReply updateStrategyVisitorLimit(@RequestBody ParamVisitorLimit paramVisitorLimit);


    /***
     * update method strategy visitor average
     * @param methodName
     * @param upper
     * @param lower
     * @return
     */
    @RequestMapping(value = "/strategy/methodAverage/update", method = RequestMethod.PUT)
    public CommonReply updateStrategyVisitorAverage(@RequestParam(name = "methodName") String methodName, @RequestParam(name = "upper") long upper, @RequestParam(name = "lower") long lower, @RequestParam(name = "switch") boolean sswitch);


    /***
     *  set strategy method response time
     * @param paramTimeWindow
     * @return
     */
    @RequestMapping(value = "/strategy/timeWindow/update", method = RequestMethod.PUT)
    public CommonReply updateStrategyTimeWindow(@RequestBody ParamTimeWindow paramTimeWindow);

    @RequestMapping("/strategy/overview")
    public Overview getOverview();

    @RequestMapping("/strategy/timeWindow/info")
    public List<Strategy_timeWindow> getTimeWindow();


    @RequestMapping("/strategy/serviceTimePeriod/info")
    public Strategy_timePeriod getTimePeriod(@RequestParam("serviceName")String serviceName);

    @RequestMapping("/strategy/serviceVisitorLimit/info")
    public Strategy_visitorLimit getVisitorLimit(@RequestParam("serviceName")String serviceName);

    @RequestMapping("/strategy/serviceMethodAverage/info")
    public List<Strategy_visitorAverage> getMethodAverage();


    @RequestMapping("/strategy/timeWindow/change")
    public CommonReply changeStrategyTimeWindow();

    @RequestMapping("/strategy/serviceTimePeriod/{serviceName}/change")
    public CommonReply changeStrategyServiceTimeperiod(@PathVariable("serviceName") String serviceName);

    @RequestMapping("/strategy/serviceVisitorLimit/{serviceName}/change")
    public CommonReply changeStrategyServiceVisitorLimit(@PathVariable("serviceName")String serviceName);

    @RequestMapping("/strategy/manual/{serviceName}/up")
    public CommonReply manualServiceUp(@PathVariable("serviceName")String serviceName);

    @RequestMapping("/strategy/manual/{serviceName}/down")
    public CommonReply manualServiceDown(@PathVariable("serviceName")String serviceName);
}
