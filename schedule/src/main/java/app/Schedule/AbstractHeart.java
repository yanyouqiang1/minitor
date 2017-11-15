package app.Schedule;

import app.database.service.StrategyContainerService;
import app.database.service.StrategyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;

/**
 * Created by Administrator on 2017/8/29.
 */
public abstract class AbstractHeart {

    @Autowired
    StrategyContainerService containerService;

    @Autowired
    StrategyRecordService recordService;

    @Scheduled(initialDelay = 0, fixedRate = 60 * 1000)
    public void heartbeat() {
//        Set<AbstractService> upService = new HashSet<AbstractService>();
//        Set<AbstractService> downService = new HashSet<AbstractService>();
        Set<OperationService> upService = new HashSet<>();
        Set<OperationService> downService = new HashSet<>();

        //服务调度
        List<AbstractService> allService = getAllStrategyService();
        containerService.save(allService);//保存服务容器数量
        for (AbstractService service : allService) {
            //服务单个调度
            List<ServiceSingleStrategyInter> serviceSingleStrategyInterList = service.getServiceSingleStrategyInterList();
            Iterator iterator = serviceSingleStrategyInterList.iterator();
            boolean isServiceHandle = false;
            while (iterator.hasNext() && !isServiceHandle) {
                ServiceSingleStrategyInter serviceSingleStrategyInter = (ServiceSingleStrategyInter) iterator.next();
                OperationService operationService;
                switch (serviceSingleStrategyInter.doStrategy(service)) {
                    case UP:
                        operationService = new OperationService(service,serviceSingleStrategyInter.getStrategyName());
                        upService.add(operationService);
                        isServiceHandle = true;
                        break;
                    case DOWN:
                        operationService = new OperationService(service,serviceSingleStrategyInter.getStrategyName());
                        downService.add(operationService);
                        isServiceHandle = true;
                        break;
                    default:
                        break;
                }
                serviceSingleStrategyInter.afterStrategy(service);
            }
            //方法调度
//            List<AbstractMethod> methods = getStrategyMethods(service);
//            service.setMethodList(methods);
//            if(methods!=null) {
//                for (AbstractMethod method : methods) {
//                    //方法单个策略
//                    List<MethodSingleStrategyInter> methodSingleStrategyInters = method.getMethodSingleStrategyInterList();
//                    iterator = methodSingleStrategyInters.iterator();
//                    boolean isMethodHandle = false;
//                    while (iterator.hasNext() && !isMethodHandle) {
//                        MethodSingleStrategyInter methodSingleStrategyInter = (MethodSingleStrategyInter) iterator.next();
//                        OperationService operationService;
//                        switch (methodSingleStrategyInter.doStrategy(method, service)) {
//                            case UP:
//                                operationService = new OperationService(service,methodSingleStrategyInter.getStrategyName());
//                                upService.add(operationService);
//                                isMethodHandle = true;
//                                break;
//                            case DOWN:
//                                operationService = new OperationService(service,methodSingleStrategyInter.getStrategyName());
//                                downService.add(operationService);
//                                isMethodHandle = true;
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                }
//            }
        }

        //全局策略
        List<OverallStrategyInter> overallStrategyInterList = getOverallStrategy();
        for (OverallStrategyInter overallStrategyInter : overallStrategyInterList) {
            StrategyOverallResult result = overallStrategyInter.doStrategy(allService);
            OperationService operationService;
            for (AbstractService up:result.getUpList()){
                operationService = new OperationService(up,overallStrategyInter.getStrategyName());
                upService.add(operationService);
            }
            for (AbstractService down:result.getDownList()){
                operationService = new OperationService(down,overallStrategyInter.getStrategyName());
                downService.add(operationService);
            }
        }
        //方法调度

        //调度
        for (OperationService operationService : upService) {
            if(upgrade(operationService.service)){
                recordService.recordAutoUp(operationService);
            }

        }

        for (OperationService operationService : downService) {
            if(decline(operationService.service)){
                recordService.recordAutoDown(operationService);
            }
        }


    }


    protected abstract boolean decline(AbstractService service);

    protected abstract boolean upgrade(AbstractService service);

    protected abstract List<OverallStrategyInter> getOverallStrategy();

    /***
     *  获取有调度策略的方法
     * @param service
     * @return
     */
    protected abstract List<AbstractMethod> getStrategyMethods(AbstractService service);

    /***
     *  获取有策略的服务组
     * @return
     */

    protected abstract List<AbstractService> getAllStrategyService();

}
