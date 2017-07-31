package app.handle.commonHandle.warehouse.statistics;

import entitylib.MonitorMessage;

/**
 * Created by Administrator on 2017/7/11.
 */
public abstract class AbstractServiceStatistics implements Statistics {
    protected long visitors;

    protected Long id;
    protected String name;


    @Override
    public void msgRecive(MonitorMessage monitorMessage) {
        this.visitors++;
        update(monitorMessage);
    }

    public abstract void update(MonitorMessage monitorMessage);

    @Override
    public void sumup() {
        handleResult(this);
    }

    public abstract void handleResult(AbstractServiceStatistics service);

    @Override
    public void clear() {
        this.visitors = 0;
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

    public long getVisitors() {
        return visitors;
    }

    public void setVisitors(long visitors) {
        this.visitors = visitors;
    }
}
