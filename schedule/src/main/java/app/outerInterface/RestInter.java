package app.outerInterface;

import app.database.domain.Strategy_timeWindow;
import app.database.domain.Strategy_timePeriod;
import app.database.domain.Strategy_visitorAverage;
import app.database.domain.Strategy_visitorLimit;
import app.outerInterface.entity.observation.*;
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
    @RequestMapping(value = "/observation/services",method = RequestMethod.GET)
    public RestServices getRestService();
    /***
     *  单个服务响应时间
     * @return
     */
    @RequestMapping(value = "/observation/service/{serviceName}/response")
    public RestServiceResponse getRestServiceResponse(@PathVariable(name = "serviceName",required = true)String serviceName);

    /***
     * 单个服务容器变化
     * @return
     */
    @RequestMapping(value = "/observation/service/{serviceName}/container")
    public RestServiceContainer getRestServiceContainer(@PathVariable(name = "serviceName",required = true)String serviceName);

    /***
     *  单个服务所有容器CPU使用情况
     * @return
     */
    public RestServiceContainerCpu getRestServiceContainerCpu(@PathVariable(name = "serviceName",required = true)String serviceName);

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
    public AutomaticList getAutomaticList();


    /***
     *  get manual list
     * @return
     */
    @RequestMapping("/task/manualList")
    public ManualList getManualList();

    /***
     *  set strategy time period
     * @param serviceName
     * @param peek
     * @param thought
     * @return
     */
    @RequestMapping("/strategy/serviceTimePeriod/update")
    public CommonReply updateStrategyTimePeriod(@RequestParam(name = "serviceName") String serviceName, @RequestParam(name = "peek") String peek, @RequestParam(name = "thought") String thought, @RequestParam(name = "switch") boolean sswitch);

    /***
     *  set strategy visitor limit
     * @param serviceName
     * @param upper
     * @param lower
     * @return
     */
    @RequestMapping("/strategy/serviceVisitorLimit/update")
    public CommonReply updateStrategyVisitorLimit(@RequestParam(name = "serviceName")String serviceName,@RequestParam(name = "upper")long upper,@RequestParam(name = "lower")long lower, @RequestParam(name = "switch") boolean sswitch);


    /***
     * update method strategy visitor average
     * @param methodName
     * @param upper
     * @param lower
     * @return
     */
    @RequestMapping("/strategy/methodAverage/update")
    public CommonReply updateStrategyVisitorAverage(@RequestParam(name = "methodName")String methodName,@RequestParam(name = "upper")long upper,@RequestParam(name = "lower")long lower, @RequestParam(name = "switch") boolean sswitch);

    /***
     *  set overall strategy switch
     * @param strategyName
     * @param sswitch
     * @return
     */
    public CommonReply updateStrategyOverallSwitch(String strategyName,boolean sswitch);

    /***
     *  set strategy method response time
     * @param methodName
     * @param lower
     * @param upper
     * @param upperLimit
     * @return
     */
    @RequestMapping("/strategy/timeWindow/update")
    public CommonReply updateStrategyTimeWindow(@RequestParam(name = "methodName")String methodName,@RequestParam(name = "lower")int lower,@RequestParam(name = "upper")int upper,@RequestParam(name = "upperLimit")int upperLimit);

    @RequestMapping("/strategy/overview")
    public Overview getOverview();

    @RequestMapping("/strategy/timeWindow/info")
    public List<Strategy_timeWindow> getTimeWindow();



    @RequestMapping("/strategy/serviceTimePeriod/info")
    public List<Strategy_timePeriod> getTimePeriod();

    @RequestMapping("/strategy/serviceVisitorLimit/info")
    public List<Strategy_visitorLimit> getVisitorLimit();

    @RequestMapping("/strategy/serviceMethodAverage/info")
    public List<Strategy_visitorAverage> getMethodAverage();

}
