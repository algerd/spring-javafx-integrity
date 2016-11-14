
package ru.javafx.jfxintegrity;

import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ControllerFactory implements Callback<Class<?>, Object> {
    
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Object call(Class<?> type) {
        return applicationContext.getBean(type);
    }

}
