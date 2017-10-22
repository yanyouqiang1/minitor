package app.webInterface;

import app.webInterface.entity.observation.*;

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



}
