package app.schedule.rancher.rancherUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拓扑图
 * Created by Administrator on 2017/6/5 0005.
 */
public class TopologyMap {
    private int size;

    private Map<String,Integer> stringMaps;
    private int[][] relation; //关系数组

    public TopologyMap(List<String> strings) {
        this.size = strings.size();
        stringMaps = new HashMap<String, Integer>();
        for(int i=0;i<size;i++){
            stringMaps.put(strings.get(i),i);
        }
        relation = new int[size][size];
    }

    /***
     * 添加关系
     * @param source 起始点
     * @param des 终止列表
     */
    public void addRelation(String source,List<String> des){
        for(String str:des){
            relation[stringMaps.get(source)][stringMaps.get(str)]=1;
        }
    }

    /***
     * 获取关系
     * @param source 起始点
     * @param des 终止点
     * @return
     */
    public boolean judgeRelation(String source,String des){
        if(relation[stringMaps.get(source)][stringMaps.get(des)]==1){
            return true;
        }else
            return false;
    }

    public Map<String, Integer> getStringMaps() {
        return stringMaps;
    }

    public int[][] getRelation() {
        return relation;
    }
}
