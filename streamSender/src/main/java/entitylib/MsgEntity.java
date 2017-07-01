package entitylib;

/**
 * Created by Administrator on 2017/6/3 0003.
 */
public class MsgEntity {
    private String seviceName;
    //响应时间
    private int responseTime;

    public MsgEntity(String seviceName, int responseTime) {
        this.seviceName = seviceName;
        this.responseTime = responseTime;
    }

    public String getSeviceName() {
        return seviceName;
    }

    public void setSeviceName(String seviceName) {
        this.seviceName = seviceName;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }
}
