package app.aspect;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by jeese on 2017/7/29.
 */
public interface RabbitChannel {

    String OUTPUT_CHANNEL_service = "output_channel_service";

    @Output(RabbitChannel.OUTPUT_CHANNEL_service)
    MessageChannel requestOutPut();
}
