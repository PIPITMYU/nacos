package com.demo.dynamic.aspectj;


import com.demo.dynamic.config.DataSourceHolder;
import com.demo.dynamic.annotation.ChooseSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/18
 * Time: 13:55
 * Description: No Description
 */
@Aspect
//优先级要设置在事务切面执行之前
@Order(1)
@Component
@Slf4j
public class DataSourceAspect {
    @Pointcut("@annotation(com.demo.dynamic.annotation.ChooseSource)")
    public void pointcut() {
    }

    /**
     * 在方法执行之前切换到指定的数据源
     * @param joinPoint
     */
    @Before(value = "pointcut()")
    public void beforeOpt(JoinPoint joinPoint) {
        /*因为是对注解进行切面，所以这边无需做过多判定，直接获取注解的值，进行环绕，将数据源设置成远方，然后结束后，清楚当前线程数据源*/
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        ChooseSource chooseSource = method.getAnnotation(ChooseSource.class);
        log.info("[Switch DataSource]:" + chooseSource.value());
        DataSourceHolder.setDataSource(chooseSource.value());
    }

    /**
     * 方法执行之后清除掉ThreadLocal中存储的KEY，这样动态数据源会使用默认的数据源
     */
    @After(value = "pointcut()")
    public void afterOpt() {
        DataSourceHolder.clearDataSource();
        log.info("[Choose Default DataSource]");
    }
}
