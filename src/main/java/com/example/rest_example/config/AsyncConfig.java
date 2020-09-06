package com.example.rest_example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync //обнаруживает аннотацию Spring @Async. Включает возможность выполнения асинхронных методов Spring
public class AsyncConfig {

    @Bean(name ="taskExecutor")
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor(); //JavaBean, который позволяет настраивать ThreadPoolExecutor стиль bean-компонента (через его свойства «corePoolSize», «maxPoolSize», «keepAliveSeconds», «queueCapacity») и выставлять его как Spring TaskExecutor.
        executor.setCorePoolSize(2); //Установите размер основного пула ThreadPoolExecutor.
        executor.setMaxPoolSize(2); //Установите максимальный размер пула ThreadPoolExecutor
        executor.setQueueCapacity(100); //Установите емкость BlockingQueue ThreadPoolExecutor.
        executor.setThreadNamePrefix("sellerThread-"); //Укажите префикс, который будет использоваться для имен вновь созданных потоков. По умолчанию - SimpleAsyncTaskExecutor-
        executor.initialize(); //настройка ExecutorService.
        return executor;
    }
}
