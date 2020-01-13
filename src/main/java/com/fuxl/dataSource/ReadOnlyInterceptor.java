package com.fuxl.dataSource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class ReadOnlyInterceptor implements Ordered {
    private static Logger LOGGER = LoggerFactory.getLogger(ReadOnlyInterceptor.class);

    @Around("@annotation(ReadOnly)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint) {
        DataBaseContextHolder.setDataBasicType(DataBaseContextHolder.DataBaseType.SLAVE);
        LOGGER.info("======SLAVE CONNECTION======");
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            DataBaseContextHolder.clearDataBasicType();
        }
        return result;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
