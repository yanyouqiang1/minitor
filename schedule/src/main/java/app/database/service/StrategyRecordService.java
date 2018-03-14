package app.database.service;

import app.Schedule.AbstractService;
import app.Schedule.OperationService;
import app.database.dao.StrategyContainerRepository;
import app.database.dao.StrategyRecordRepository;
import app.database.domain.Strategy_container;
import app.database.domain.Strategy_record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Date;
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
        record.setEffect(operationService.getService().getName()+" UP "+operationService.getSize());
        record.setTime(operationService.getTime());
        record(record);

    }
    public void recordAutoDown(OperationService operationService){
        Strategy_record record = new Strategy_record();
        record.setIsAuto(true);
        record.setServiceName(operationService.getService().getName());
        record.setStrategyName(operationService.getStrategyName());
        record.setEffect(operationService.getService().getName()+" DOWN "+operationService.getSize());
        record.setTime(operationService.getTime());
        record(record);
    }


    public void recordManualUp(String serviceName){
        Strategy_record record = new Strategy_record();
        record.setIsAuto(false);
        record.setServiceName(serviceName);
        record.setEffect(serviceName+" UP 1");
        record.setTime(new Date());
        record(record);
    }
    public void recordManualDown(String serviceName){
        Strategy_record record = new Strategy_record();
        record.setIsAuto(false);
        record.setServiceName(serviceName);
        record.setEffect(serviceName+" DOWN 1");
        record.setTime(new Date());
        record(record);
    }
    private void record(Strategy_record record){
        recordRepository.save(record);
    }

    public List<Strategy_record> getAutomaticList(int page,int size){
        Pageable pageable = new PageRequest(page,size);
        return recordRepository.findByisAutoEquals(pageable,true);
    }
    public List<Strategy_record> getManualList(int page,int size){
        Pageable pageable = new PageRequest(page,size);
        return recordRepository.findByisAutoEquals(pageable,false);
    }
}
