package org.omidmohebbise.async.service;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class ServiceConsumer {

    private int callPerSecond = 1;
    private final AsyncServiceExample asyncServiceExample;
    private final ApplicationContext applicationContext;

    public ServiceConsumer(AsyncServiceExample asyncServiceExample, ApplicationContext applicationContext) {
        this.asyncServiceExample = asyncServiceExample;
        this.applicationContext = applicationContext;
    }


    @Scheduled(fixedRate = 4000)
    public void scheduleAsync() {
        ThreadPoolTaskExecutor executor = applicationContext.getBean(ThreadPoolTaskExecutor.class);

        System.out.printf("ThreadPool Information: Active Threads: %d, Core Pool Size: %d, Max Pool Size: %d, Keep Alive Time: %d seconds, Queue Size: %d, Queue Capacity: %d%n",
                executor.getActiveCount(), executor.getCorePoolSize(), executor.getMaxPoolSize(), executor.getKeepAliveSeconds(),
                executor.getThreadPoolExecutor().getQueue().size(), executor.getThreadPoolExecutor().getQueue().remainingCapacity());

    }

    @Scheduled(fixedRate = 800)
    public void consumer() {
        for (int i = 0; i < callPerSecond; i++) {
            asyncServiceExample.asyncMethod();
        }

    }

}
