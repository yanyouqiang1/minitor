package app.messagechannel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Administrator on 2017/7/30.
 */
public interface RabbitChannel {
    String INPUT_REQUEST = "input_channel_request";
    String INPUT_RESPONSE = "input_channel_response";
    String INPUT__SERVICE = "input_channel_service";

    @Input(RabbitChannel.INPUT_REQUEST)
    SubscribableChannel inputRequest();

    @Input(RabbitChannel.INPUT_RESPONSE)
    SubscribableChannel inputResponse();


    @Input(RabbitChannel.INPUT__SERVICE)
    SubscribableChannel inputService();
}
