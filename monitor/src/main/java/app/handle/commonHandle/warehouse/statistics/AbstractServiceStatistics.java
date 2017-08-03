package app.handle.commonHandle.warehouse.statistics;

import entitylib.RequestMessage;
import entitylib.ResponseMessage;
import lombok.Getter;

/**
 * Created by Administrator on 2017/7/11.
 */
public abstract class AbstractServiceStatistics implements Statistics {
    @Getter
    protected long response_visitors,request_visitors;

    protected Long id;
    protected String name;


    @Override
    public void msgReceive(ResponseMessage responseMessage) {
        this.response_visitors++;
        update(responseMessage);
    }

    @Override
    public void msgReceive(RequestMessage requestMessage) {
        this.request_visitors++;
    }

    public abstract void update(ResponseMessage responseMessage);

    @Override
    public void sumup() {
        handleResult(this);
    }

    public abstract void handleResult(AbstractServiceStatistics service);

    @Override
    public void clear() {
        this.response_visitors = 0;
        this.request_visitors= 0;
        attributeClear();
    }

    public abstract void attributeClear();

    @Override
    public void statisticsUpdate() {

    }

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

}
