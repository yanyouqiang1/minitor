package app.webInterface.entity.list;

import app.webInterface.entity.method.Method;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
public class Details {
    List<Method> methods;

    public void generate(List<Method> methods){
        this.methods = methods;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }
}
