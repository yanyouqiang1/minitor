package app.handle.commonHandle.warehouse.statistics;

import entitylib.MsgEntity;

/**
 * Created by Administrator on 2017/7/11.
 */
public abstract class AbstractServiceStatistics implements Statistics{
    long visitors;

    protected long serviceid;
    protected String name;


    @Override
    public void msgRecive(MsgEntity msgEntity){
        this.visitors++;
        update(msgEntity);
    }
    public abstract void update(MsgEntity msgEntity);

    @Override
    public void sumup(){
        handleResult(this);
    }
    public abstract void handleResult(AbstractServiceStatistics service);

    @Override
    public void clear() {
        this.visitors=0;
        attributeClear();
    }
    public abstract void attributeClear();
    @Override
    public void statisticsUpdate() {

    }

    public long getServiceid() {
        return serviceid;
    }
    public void setServiceid(long serviceid) {
        this.serviceid = serviceid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
