package app;

        import app.manage.RancherConfig;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
@SpringBootApplication
@EnableConfigurationProperties({RancherConfig.class})
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class,args);
    }
}
