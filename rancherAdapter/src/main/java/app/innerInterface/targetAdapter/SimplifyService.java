package app.innerInterface.targetAdapter;

import lombok.Data;

import java.util.List;

@Data
public class SimplifyService {
    List<AdapterService> adapterServices;

    public SimplifyService() {
    }
}
