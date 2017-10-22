//package app.feignclient;
//
//import app.feignclient.monitor.Monitor;
//import app.feignclient.monitor.MonitorMethod;
//import org.springframework.stereotype.Component;
//
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * Created by Administrator on 2017/9/19.
// */
//@Component
//public class TestMonitor implements Monitor {
//    @Override
//    public List<MonitorMethod> getServiceMethods(String serviceName) {
//        List<MonitorMethod> monitorMethodList = new LinkedList<>();
//        MonitorMethod method;
//        if (serviceName.equals("serviceA")) {
//            method = new MonitorMethod();
//            method.setName("methodA1");
//            monitorMethodList.add(method);
//            method = new MonitorMethod();
//            method.setName("methodA2");
//            monitorMethodList.add(method);
//        } else if (serviceName.equals("serviceB")) {
//            method = new MonitorMethod();
//            method.setName("methodB1");
//            monitorMethodList.add(method);
//            method = new MonitorMethod();
//            method.setName("methodB2");
//            monitorMethodList.add(method);
//        } else if (serviceName.equals("serviceC")) {
//            method = new MonitorMethod();
//            method.setName("methodC1");
//            monitorMethodList.add(method);
//            method = new MonitorMethod();
//            method.setName("methodC2");
//            monitorMethodList.add(method);
//        }
//        return monitorMethodList;
//    }
//
//    @Override
//    public long getServiceLatestVisitor(String serviceName) {
//        return 0;
//    }
//
//    @Override
//    public long getMethodLatestVisitor(String serviceName, String methodName) {
//        return 0;
//    }
//
//    @Override
//    public int[] getMethodRecentResponseTime(String serviceName, String methodName) {
//        return new int[]{10,12,20,15,16,31,20,15,16,31,17,12,12,12};
//    }
//
//    @Override
//    public int[] getServiceRecentResponseTime(String serviceName) {
//        return new int[0];
//    }
//}
