package app.outerInterface.entity.re;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParamTimeWindow {
    private String serviceName;
    private int upper;
    private int lower;
    private int upperLimit;
}
