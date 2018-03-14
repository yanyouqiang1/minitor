package app.handle.commonHandle.servicehouse;

import entitylib.ServiceMessage;

public interface ServiceHouseInter {

    public void messageReceive(ServiceMessage serviceMessage);

    public void sumup();
}
