package app.handle.commonHandle.warehouse;

import app.handle.commonHandle.warehouse.msgAnalyisis.Singlestatistics;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public interface HistoryDataListener {
    /**
     * 处理历史数据
     * @param servcieName 服务名
     * @param statistics 数据
     */
    public void handle(String servcieName, Singlestatistics statistics);
}
