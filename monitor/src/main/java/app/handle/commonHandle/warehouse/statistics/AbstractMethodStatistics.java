package app.handle.commonHandle.warehouse.statistics;

import entitylib.RequestMessage;
import entitylib.ResponseMessage;
import lombok.Getter;

/**
 * Created by Administrator on 2017/7/6.
 */
public abstract class AbstractMethodStatistics implements Statistics {
    //统计属性
    @Getter
    protected long response_visitors,request_visitors;

    //自身属性
    protected Long id;
    protected String name;

    //映射属性
    private AbstractResourceStatistics parentResource;


    public void msgReceive(ResponseMessage responseMessage) {
        this.response_visitors++;
        update(responseMessage);
    }

    @Override
    public void msgReceive(RequestMessage requestMessage) {
        this.request_visitors++;
    }

    public abstract void update(ResponseMessage responseMessage);

    public void sumup() {
        handleResult(this);
        clear();
    }

    public abstract void handleResult(AbstractMethodStatistics method);

    @Override
    public void statisticsUpdate() {

    }

    public void clear() {
        response_visitors = 0l;
        request_visitors =0l;
        attributeClear();
    }

    public abstract void attributeClear();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbstractResourceStatistics getParentResource() {
        return parentResource;
    }

    public void setParentResource(AbstractResourceStatistics parentResource) {
        this.parentResource = parentResource;
    }

}
