import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import testjson.*;

import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 */
public class test {
    public static void main(String[] args) {
        String json = "{\n" +
                "  \"id\" : 1,\n" +
                "  \"groupName\" : \"S1Group\",\n" +
                "  \"description\" : \"测试s1\",\n" +
                "  \"resources\" : [ {\n" +
                "    \"id\" : 1,\n" +
                "    \"groupId\" : 1,\n" +
                "    \"serviceId\" : 1,\n" +
                "    \"resourceName\" : \"hello1\",\n" +
                "    \"description\" : \"测试s1\",\n" +
                "    \"path\" : \"/hello/*\",\n" +
                "    \"retryable\" : true,\n" +
                "    \"enabled\" : true,\n" +
                "    \"order\" : 2,\n" +
                "    \"sensitiveHeaders\" : null,\n" +
                "    \"service\" : {\n" +
                "      \"id\" : 1,\n" +
                "      \"serviceName\" : \"s1\",\n" +
                "      \"description\" : null,\n" +
                "      \"serviceId\" : \"s1-service\",\n" +
                "      \"url\" : null,\n" +
                "      \"active\" : true\n" +
                "    },\n" +
                "    \"methods\" : [ {\n" +
                "      \"id\" : 1,\n" +
                "      \"resourceId\" : 1,\n" +
                "      \"authorityId\" : 1,\n" +
                "      \"methodName\" : \"GET\",\n" +
                "      \"description\" : \"测试s1\",\n" +
                "      \"authentication\" : true,\n" +
                "      \"authorization\" : true,\n" +
                "      \"enabled\" : true,\n" +
                "      \"active\" : true\n" +
                "    } ],\n" +
                "    \"active\" : true\n" +
                "  }";
        Rootbean rootbean =Rootbean.objectFromData(json);

        System.out.println();
    }

}
