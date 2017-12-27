package app.outerInterface.entity.re;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParamVisitorLimit {
    private String serviceName;
    private int upper;
    private int lower;
}
