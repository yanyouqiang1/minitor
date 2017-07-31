package app.handle.commonHandle.warehouse.statistics;

import entitylib.MonitorMessage;

/**
 * Created by Administrator on 2017/7/6.
 */
public abstract class AbstractMethodStatistics implements Statistics {
    //统计属性
    protected long visitors;

    //自身属性
    protected Long id;
    protected String name;

    //映射属性
    private AbstractResourceStatistics parentResource;


    public void msgRecive(MonitorMessage monitorMessage) {
        this.visitors++;
        update(monitorMessage);
    }

    public abstract void update(MonitorMessage monitorMessage);

    public void sumup() {
        handleResult(this);
        clear();
    }

    public abstract void handleResult(AbstractMethodStatistics method);

    @Override
    public void statisticsUpdate() {

    }

    public void clear() {
        visitors = 0l;
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

    public long getVisitors() {
        return visitors;
    }

    public void setVisitors(long visitors) {
        this.visitors = visitors;
    }
}
