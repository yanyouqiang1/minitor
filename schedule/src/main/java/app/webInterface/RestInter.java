package app.webInterface;

import app.webInterface.entity.observation.*;
import app.webInterface.entity.setting.CommonReply;

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
     *  服务拓扑结构图
     * @return
     */
    public RestServiceTopology getServiceTopology();

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
     *  设置服务扩容的webhook
     * @param serviceName
     * @param upUrl
     */
    public CommonReply setWebhookUp(String serviceName, String upUrl);

    /***
     *  设置服务缩小的webhook
     * @param serviceName
     * @param downUrl
     */
    public CommonReply setWebhookDown(String serviceName, String downUrl);

    /***
     *  设置方法的上阈值
     * @param methodID
     * @param upperThreshold
     */
    public CommonReply setMethodThresholdUpper(Long methodID, int upperThreshold);

    /***
     *  设置方法的下阈值
     * @param methodID
     * @param lowerThreshold
     */
    public CommonReply setMethodThresholdLower(Long methodID, int lowerThreshold);

}
