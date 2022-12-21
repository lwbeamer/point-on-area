package com.example.demo.annotations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Component
@Slf4j
public class LoggerBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(LogInitMethods.class)) {
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                if (methods[i].isAnnotationPresent(Loggable.class)) {
                    String message = "Method "+methods[i].getName()+" has been initialized";
                    log.info(message);
                }
            }
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}

