package app.database.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/7/5.
 */
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;

    public User(int i, String s) {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}