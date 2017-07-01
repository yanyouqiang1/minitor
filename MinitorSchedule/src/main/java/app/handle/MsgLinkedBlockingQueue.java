package app.handle;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
public class MsgLinkedBlockingQueue extends LinkedBlockingDeque {
    public static int QueSize = 30;
    @Override
    public void addFirst(Object o) {
        if(super.size()>QueSize){
            super.removeLast();
        }
        super.addFirst(o);
    }
}
