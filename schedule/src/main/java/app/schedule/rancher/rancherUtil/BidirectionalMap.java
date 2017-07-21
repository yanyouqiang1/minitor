package app.schedule.rancher.rancherUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5 0005.
 * 双向Map
 */
public class BidirectionalMap<K,V>{
    List<K> listK1;
    List<V> listK2;

    public BidirectionalMap() {
        this.listK1 = new ArrayList<K>();
        this.listK2 = new ArrayList<V>();
    }

    public int size(){
        return listK1.size();
    }

    public boolean isEmpty(){
        return listK1.isEmpty();
    }

    public K getK(V k2){
        return listK1.get(listK2.indexOf(k2));
    }

    public V getV(K k1){
        return listK2.get(listK1.indexOf(k1));
    }

    public void put(K k,V v){
        listK1.add(k);
        listK2.add(v);
    }

    public List<K> getListK1() {
        return listK1;
    }

    public List<V> getListK2() {
        return listK2;
    }
}
