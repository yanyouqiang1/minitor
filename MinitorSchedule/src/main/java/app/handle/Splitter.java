package app.handle;

import entitylib.MsgEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
@Component
public class Splitter implements MsgReciveInter{

    private static Map msgMap = new HashMap<String,MsgLinkedBlockingQueue>();

    public static Map getMsgMap() {
        return msgMap;
    }

    @Override
    public void msgRecived(MsgEntity msgEntity) {
        MsgLinkedBlockingQueue queue = (MsgLinkedBlockingQueue) msgMap.get(msgEntity.getSeviceName());
        if(queue==null){
            queue = new MsgLinkedBlockingQueue();
            msgMap.put(msgEntity.getSeviceName(),queue);
        }
        queue.addFirst(msgEntity);
    }
}