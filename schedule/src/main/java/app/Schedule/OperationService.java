package app.Schedule;

import lombok.Data;

import java.util.Date;

@Data
public class OperationService {
    AbstractService service;
    String strategyName;
    int size; //size default 1
    Date time;

    public OperationService(AbstractService service, String strategyName) {
        this.service = service;
        this.strategyName = strategyName;
        this.size = 1;
        this.time = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OperationService that = (OperationService) o;

        return service != null ? service.equals(that.service) : that.service == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (service != null ? service.hashCode() : 0);
        return result;
    }
}
