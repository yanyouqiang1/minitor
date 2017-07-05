package app;

import app.handle.HandleInter;
import app.handle.commonHandle.CommonHandleImpl;
import app.handle.commonHandle.Msgfilter;
import app.handle.commonHandle.warehouse.HistoryDataListener;
import app.handle.commonHandle.warehouse.MsgWarehouseImpl;
import app.handle.commonHandle.warehouse.WarehoseInter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
@Configuration
public class Config {
    @Autowired
    HistoryDataListener historyDataListener;

    @Bean
    public WarehoseInter getWarehose(){
        MsgWarehouseImpl msgWarehouse = new MsgWarehouseImpl();
        msgWarehouse.setHistoryDataListener(historyDataListener);
        return msgWarehouse;
    }

    @Autowired @Qualifier("httpStatusFilter")
    Msgfilter httpStatusFilter;

    @Autowired @Qualifier("methodFilter")
    Msgfilter methodFilter;

    @Bean
    public HandleInter getcommonHandle(){
        CommonHandleImpl commonHandle  = new CommonHandleImpl();
        commonHandle.getMsgfilterChain().add(httpStatusFilter);
        commonHandle.getMsgfilterChain().add(methodFilter);
        return commonHandle;
    }

    @Bean
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        template.setValueSerializer(jackson2JsonRedisSerializer); //1
        template.setKeySerializer(new StringRedisSerializer()); //2

        template.afterPropertiesSet();
        return template;
    }
}
