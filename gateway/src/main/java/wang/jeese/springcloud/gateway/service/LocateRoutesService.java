package wang.jeese.springcloud.gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import wang.jeese.springcloud.gateway.model.*;

import java.util.*;

/**
 * Created by jeese on 2017/6/1.
 */
@org.springframework.stereotype.Service
public class LocateRoutesService {

    private static Logger logger = LoggerFactory.getLogger(LocateRoutesService.class);

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private MethodRepository methodRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    public Map<String, ZuulProperties.ZuulRoute> locateRoutesFromDB(){

        List<Resource> resourceList = resourceRepository.findAllByIsActiveIsTrueAndEnabledIsTrue();
        List<Method> methodList = methodRepository.findAllByIsActiveIsTrueAndEnabledIsTrue();
        List<Service> serviceList = serviceRepository.findAllByIsActiveIsTrue();
        ComparatorOrder comparatorOrder = new ComparatorOrder();
        resourceList.sort(comparatorOrder);
        Map<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();
        Map<String, Resource> resourceMap = new LinkedHashMap<>();
        Map<Long, Service> serviceMap = new LinkedHashMap<>();
        for(Resource resource : resourceList){
            resourceMap.put(resource.getId().toString(), resource);
        }
        for(Service service : serviceList){
            serviceMap.put(service.getId(), service);
        }
        for(Method method : methodList){
            resourceMap.get(method.getResourceId().toString()).getMethods().put(method.getMethodName(), method);
        }
        for (Map.Entry<String, Resource> entry : resourceMap.entrySet()) {
            entry.getValue().setService(serviceMap.get(entry.getValue().getServiceId()));
            if(org.apache.commons.lang3.StringUtils.isBlank(entry.getValue().getPath()) ||
                    (org.apache.commons.lang3.StringUtils.isBlank(entry.getValue().getService().getUrl())
                            && org.apache.commons.lang3.StringUtils.isBlank(entry.getValue().getService().getServiceId()))){
                continue;
            }
            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            try {
                zuulRoute.setId(entry.getValue().getId().toString());
                zuulRoute.setPath(entry.getValue().getPath());
                zuulRoute.setUrl(entry.getValue().getService().getUrl());
                zuulRoute.setServiceId(entry.getValue().getService().getServiceId());
                zuulRoute.setRetryable(entry.getValue().getRetryable());
                zuulRoute.setStripPrefix(false);
                if(null != entry.getValue().getSensitiveHeaders()){
                    if(!entry.getValue().getSensitiveHeaders().equals("default")){
                        String[] strings = entry.getValue().getSensitiveHeaders().split(",");
                        Set<String> header = new LinkedHashSet<>();
                        header.addAll(Arrays.asList(strings));
                        zuulRoute.setSensitiveHeaders(header);
                    }
                }
            } catch (Exception e) {
                logger.error("=============load zuul route info from db with error==============",e);
            }
            routes.put(zuulRoute.getPath(),zuulRoute);
        }
        List<Authority> authorities = authorityRepository.findAllByIsActiveIsTrueAndEnabledIsTrue();
        Map<Long, String> authorityMap = new HashMap<>();
        if(!authorities.isEmpty()){
            for(Authority authority: authorities){
                authorityMap.put(authority.getId(), authority.getAuthorityName());
            }
        }
        GatewayData.getInstance().setAuthorityMap(authorityMap);
        GatewayData.getInstance().setResourceMap(resourceMap);
        return routes;
    }

    class ComparatorOrder implements Comparator<Resource> {

        @Override
        public int compare(Resource o1, Resource o2) {
            return o2.getOrder().compareTo(o1.getOrder());
        }
    }

}
