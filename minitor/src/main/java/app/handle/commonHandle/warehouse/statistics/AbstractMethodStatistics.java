package app.handle.commonHandle.warehouse.statistics;

import entitylib.MsgEntity;

/**
 * Created by Administrator on 2017/7/6.
 */
public abstract class AbstractMethodStatistics implements Statistics{
    //统计属性
    protected long visitors;

    //自身属性
    protected long id;
    protected String name;

    //映射属性
    private AbstractResourceStatistics parentResource;


    public void msgRecive(MsgEntity msgEntity){
        this.visitors++;
        update(msgEntity);
    }

    public abstract void update(MsgEntity msgEntity);

    public void sumup(){
        handleResult(this);
        clear();
    }
    public abstract void handleResult(AbstractMethodStatistics method);

    @Override
    public void statisticsUpdate() {

    }

    public void clear(){
        visitors = 0l;
        attributeClear();
    }
    public abstract void attributeClear();

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getVisitors() {
        return visitors;
    }

    public void setVisitors(long visitors) {
        this.visitors = visitors;
    }
}
