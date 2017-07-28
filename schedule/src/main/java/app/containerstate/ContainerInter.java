package app.containerstate;

import app.containerstate.entity.ConsultData;

/**
 * Created by Administrator on 2017/7/28.
 */
public interface ContainerInter {
    /***
     *  获得CPU使用率
     * @param containerName
     * @return
     */
    public ConsultData getCpuRateByContainerName(String containerName);

    /***
     *  获取 内存使用情况
     * @param containerName
     * @return
     */
    public ConsultData getMemoryByContainerName(String containerName);

}
