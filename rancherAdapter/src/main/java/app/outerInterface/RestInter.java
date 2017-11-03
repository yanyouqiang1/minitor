package app.outerInterface;

import app.outerInterface.setting.CommonReply;

/**
 * Created by Administrator on 2017/9/8.
 */
public interface RestInter{
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
}
