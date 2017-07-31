package app.feignclient.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeese on 2017/6/23.
 */
@Data
public class Group {

    private Long id;
    private String groupName;
    private String description;
    private Boolean isActive;
    private List<Resource> resources = new ArrayList<Resource>();

    public Group() {
    }
}
