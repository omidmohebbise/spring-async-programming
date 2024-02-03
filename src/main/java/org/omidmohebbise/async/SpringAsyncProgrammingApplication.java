package org.omidmohebbise.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class SpringAsyncProgrammingApplication{

    public static void main(String[] args) {
        SpringApplication.run(SpringAsyncProgrammingApplication.class, args);
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(30);
        executor.setAllowCoreThreadTimeOut(true);
        executor.setThreadNamePrefix("Lookup-");
        executor.initialize();
        return executor;
    }


}
