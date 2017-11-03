package app.database.service;

import app.Schedule.AbstractService;
import app.Schedule.OperationService;
import app.database.dao.StrategyContainerRepository;
import app.database.dao.StrategyRecordRepository;
import app.database.domain.Strategy_container;
import app.database.domain.Strategy_record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/9/19.
 */
@Component
public class StrategyRecordService {
    @Autowired
    StrategyRecordRepository recordRepository;

    public void recordAutoUp(OperationService operationService){
        Strategy_record record = new Strategy_record();
        record.setIsAuto(true);
        record.setServiceName(operationService.getService().getName());
        record.setStrategyName(operationService.getStrategyName());
        record.setEffect(operationService.getService().getName()+" UP 1");
        record.setTime(operationService.getTime());
        record(record);

    }
    public void recordAutoDown(OperationService operationService){
        Strategy_record record = new Strategy_record();
        record.setIsAuto(true);
        record.setServiceName(operationService.getService().getName());
        record.setStrategyName(operationService.getStrategyName());
        record.setEffect(operationService.getService().getName()+" DOWN 1");
        record.setTime(operationService.getTime());
        record(record);
    }


    public void recordManualUp(OperationService operationService){
        Strategy_record record = new Strategy_record();
        record.setIsAuto(false);
        record.setServiceName(operationService.getService().getName());
        record.setStrategyName(operationService.getStrategyName());
        record.setEffect(operationService.getService().getName()+" UP 1");
        record.setTime(operationService.getTime());
        record(record);
    }
    public void recordManualDown(OperationService operationService){
        Strategy_record record = new Strategy_record();
        record.setIsAuto(false);
        record.setServiceName(operationService.getService().getName());
        record.setStrategyName(operationService.getStrategyName());
        record.setEffect(operationService.getService().getName()+" DOWN 1");
        record.setTime(operationService.getTime());
        record(record);
    }
    private void record(Strategy_record record){
        recordRepository.save(record);
    }
}
