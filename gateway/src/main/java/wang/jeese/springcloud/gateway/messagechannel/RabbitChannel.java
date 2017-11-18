package wang.jeese.springcloud.gateway.messagechannel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by jeese on 2017/7/29.
 */
public interface RabbitChannel {

    String OUTPUT_CHANNEL_Request = "output_channel_request";
    String OUTPUT_CHANNEL_Response = "output_channel_response";

    @Output(RabbitChannel.OUTPUT_CHANNEL_Request)
    MessageChannel requestOutPut();

    @Output(RabbitChannel.OUTPUT_CHANNEL_Response)
    MessageChannel responseOutPut();

}
