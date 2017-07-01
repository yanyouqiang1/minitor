import app.handle.Commander;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class testCommand {
    @Autowired
    Commander commander;
    @Test
    public void com(){
        System.out.println(123);
    }

}
