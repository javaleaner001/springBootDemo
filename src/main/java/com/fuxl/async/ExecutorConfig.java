package com.fuxl.async;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 不重写会默认线程池，不好控制
 */
@Configuration
@EnableAsync//异步
public class ExecutorConfig implements AsyncConfigurer {

    /**
     * 重写线程池
     *
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5);//核心线程数
        threadPoolTaskExecutor.setMaxPoolSize(2 * Runtime.getRuntime().availableProcessors());//最大线程数
        threadPoolTaskExecutor.setQueueCapacity(100);//queue容量
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
