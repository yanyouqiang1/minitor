package app.Schedule;

import app.database.service.StrategyContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;

/**
 * Created by Administrator on 2017/8/29.
 */
public abstract class AbstractHeart {

    @Autowired
    StrategyContainerService containerService;

    @Scheduled(initialDelay = 0, fixedRate = 60 * 1000)
    public void heartbeat() {
        Set<AbstractService> upService = new HashSet<AbstractService>();
        Set<AbstractService> downService = new HashSet<AbstractService>();
        //服务调度
        List<AbstractService> allService = getAllStrategyService();
        containerService.save(allService);//保存服务容器数量
        for (AbstractService service : allService) {
            //服务全局调度
//            List<OverallStrategyInter> serviceOverallStrategyInterList = service.getServiceOverallStrategyInterList();
//            Iterator iterator = serviceOverallStrategyInterList.iterator();
//            while (iterator.hasNext()) {
//                OverallStrategyInter strategy = (OverallStrategyInter) iterator.next();
//                StrategyOverallResult result = strategy.doStrategy(service);
//                upService.addAll(result.getUpList());
//                downService.addAll(result.getDownList());
//            }
            //服务单个调度
            List<ServiceSingleStrategyInter> serviceSingleStrategyInterList = service.getServiceSingleStrategyInterList();
            Iterator iterator = serviceSingleStrategyInterList.iterator();
            boolean isServiceHandle = false;
            while (iterator.hasNext() && !isServiceHandle) {
                ServiceSingleStrategyInter strategy = (ServiceSingleStrategyInter) iterator.next();
                switch (strategy.doStrategy(service)) {
                    case UP:
                        upService.add(service);
                        isServiceHandle = true;
                        break;
                    case DOWN:
                        isServiceHandle = true;
                        downService.add(service);
                        break;
                    default:
                        break;
                }
            }
            //方法调度
            List<AbstractMethod> methods = getStrategyMethods(service);
            service.setMethodList(methods);
            for (AbstractMethod method : methods) {
                //方法全局策略
//                List<MethodOverallStrategyInter> methodOverallStrategyInters = method.getMethodOverallStrategyInterList();
//                iterator = methodOverallStrategyInters.iterator();
//                while (iterator.hasNext()) {
//                    MethodOverallStrategyInter methodOverallStrategyInter = (MethodOverallStrategyInter) iterator.next();
//                    StrategyOverallResult result = methodOverallStrategyInter.doStrategy(method, service);
//                    upService.addAll(result.getUpList());
//                    downService.addAll(result.getDownList());
//                }
                //方法单个策略
                List<MethodSingleStrategyInter> methodSingleStrategyInters = method.getMethodSingleStrategyInterList();
                iterator = methodSingleStrategyInters.iterator();
                boolean isMethodHandle = false;
                while (iterator.hasNext() && !isMethodHandle) {
                    MethodSingleStrategyInter methodSingleStrategyInter = (MethodSingleStrategyInter) iterator.next();
                    switch (methodSingleStrategyInter.doStrategy(method, service)) {
                        case UP:
                            upService.add(service);
                            isMethodHandle = true;
                            break;
                        case DOWN:
                            isMethodHandle = true;
                            downService.add(service);
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        //全局策略
        List<OverallStrategyInter> overallStrategyInterList = getOverallStrategy();
        for (OverallStrategyInter overallStrategyInter : overallStrategyInterList) {
            StrategyOverallResult result = overallStrategyInter.doStrategy(allService);
            upService.addAll(result.getUpList());
            downService.addAll(result.getDownList());
        }
        //方法调度

        //调度
        for (AbstractService service : upService) {
            upgrade(service);
            service.up();
        }

        for (AbstractService service : downService) {
            decline(service);
            service.down();
        }


    }


    protected abstract void decline(AbstractService service);

    protected abstract void upgrade(AbstractService service);

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
