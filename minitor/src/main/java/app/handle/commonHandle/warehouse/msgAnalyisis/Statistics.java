package app.handle.commonHandle.warehouse.msgAnalyisis;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
//统计
public class Statistics {
    //总访问量
    private int totalAcess;

    public int getTotalAcess() {
        return totalAcess;
    }

    public void setTotalAcess(int totalAcess) {
        this.totalAcess = totalAcess;
    }

    public void clear(){
        totalAcess = 0;
    }
}
