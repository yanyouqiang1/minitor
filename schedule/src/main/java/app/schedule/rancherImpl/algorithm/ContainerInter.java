package app.schedule.rancherImpl.algorithm;

import app.schedule.rancherImpl.algorithm.entity.ResultData;

/**
 * Created by Administrator on 2017/7/28.
 */
public interface ContainerInter {
    /***
     *  获得CPU使用率
     * @param containerName
     * @return
     */
    public ResultData getCpuRateByContainerName(String containerName);

    /***
     *  获取 内存使用情况
     * @param containerName
     * @return
     */
    public ResultData getMemoryByContainerName(String containerName);

}
