package app.prometheus;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Administrator on 2017/7/28.
 */

@Data
@ConfigurationProperties(prefix = "prometheus")
public class PrometheusConfig {
    private String address;
}
