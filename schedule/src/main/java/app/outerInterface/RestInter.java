package app.outerInterface;

import app.outerInterface.entity.observation.*;
import app.outerInterface.entity.reply.CommonReply;
import app.outerInterface.entity.task.AutomaticList;
import app.outerInterface.entity.task.ManualList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/8/1.
 */
public interface RestInter {
    /***
     *  所有服务响应时间
     * @return
     */
    public RestServices getRestService();
    /***
     *  单个服务响应时间
     * @return
     */
    public RestServiceResponse getRestServiceResponse(String serviceName);

    /***
     * 单个服务容器变化
     * @return
     */
    public RestServiceContainer getRestServiceContainer(String serviceName);

    /***
     *  单个服务所有容器CPU使用情况
     * @return
     */
    public RestServiceContainerCpu getRestServiceContainerCpu(String serviceName);

    /***
     * 单个服务所有容器内存使用情况
     * @return
     */
    public RestServiceContainerMemory getRestServiceContainerMemory(String serviceName);

    /***
     *  get autoamtic list
     * @return
     */
    public AutomaticList getAutomaticList();


    /***
     *  get manual list
     * @return
     */
    public ManualList getManualList();

    /***
     *  set strategy time period
     * @param serviceName
     * @param peek
     * @param thought
     * @return
     */
    @RequestMapping("/strategy/serviceTimePeriod/update")
    public CommonReply updateStrategyTimePeriod(@RequestParam(name = "serviceName") String serviceName, @RequestParam(name = "peek") String peek, @RequestParam(name = "thought") String thought);

    /***
     *  set strategy visitor limit
     * @param serviceName
     * @param upper
     * @param lower
     * @return
     */
    @RequestMapping("/strategy/serviceVisitorLimit/update")
    public CommonReply updateStrategyVisitorLimit(@RequestParam(name = "serviceName")String serviceName,@RequestParam(name = "upper")long upper,@RequestParam(name = "lower")long lower);


    /***
     * update method strategy visitor average
     * @param methodName
     * @param upper
     * @param lower
     * @return
     */
    public CommonReply updateStrategyVisitorAverage(String methodName,long upper,long lower);

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
    public CommonReply updateStrategyResponseTime(String methodName,long lower,long upper,long upperLimit);


}
