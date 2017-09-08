package app.OtherSchedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Administrator on 2017/8/29.
 */
public abstract class AbstractHeart {

    @Scheduled(initialDelay = 60 * 1000, fixedRate = 60 * 1000)
    public void heartbeat() {
        Set<AbstractService> upService = new HashSet<AbstractService>();
        Set<AbstractService> downService = new HashSet<AbstractService>();
        //服务调度
        List<AbstractService> allService = getAllStrategyService();
        for (AbstractService service : allService) {
            //服务全局调度
            List<ServiceOverallStrategyInter> serviceOverallStrategyInterList = service.getServiceOverallStrategyInterList();
            Iterator iterator = serviceOverallStrategyInterList.iterator();
            while (iterator.hasNext()) {
                ServiceOverallStrategyInter strategy = (ServiceOverallStrategyInter) iterator.next();
                StrategyOverallResult result = strategy.doStrategy(service);
                upService.addAll(result.getUpList());
                downService.addAll(result.getDownList());
            }
            //服务单个调度
            List<ServiceSingleStrategyInter> serviceSingleStrategyInterList = service.getServiceSingleStrategyInterList();
            iterator = serviceSingleStrategyInterList.iterator();
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
            for (AbstractMethod method : methods) {
                //方法全局策略
                List<MethodOverallStrategyInter> methodOverallStrategyInters = method.getMethodOverallStrategyInterList();
                iterator = methodOverallStrategyInters.iterator();
                while (iterator.hasNext()) {
                    MethodOverallStrategyInter methodOverallStrategyInter = (MethodOverallStrategyInter) iterator.next();
                    StrategyOverallResult result = methodOverallStrategyInter.doStrategy(method, service);
                    upService.addAll(result.getUpList());
                    downService.addAll(result.getDownList());
                }
                //方法单个策略
                List<MethodSingleStrategyInter> methodSingleStrategyInters = method.getMethodSingleStrategyInterList();
                iterator = methodSingleStrategyInters.iterator();
                boolean isMethodHandle = false;
                while (iterator.hasNext() && !isMethodHandle) {
                    MethodSingleStrategyInter methodSingleStrategyInter = (MethodSingleStrategyInter) iterator.next();
                    switch (methodSingleStrategyInter.doStrategy(method)) {
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

        //方法调度

        //调度
        for (AbstractService service : upService) {
            service.up();
        }

        for (AbstractService service : downService) {
            service.down();
        }


    }

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
