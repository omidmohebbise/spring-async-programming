package org.omidmohebbise.async.controller;


import org.omidmohebbise.async.controller.dto.ThreadPoolInfo;
import org.omidmohebbise.async.service.AsyncServiceExample;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController

public class AsyncController {
    private final AsyncServiceExample asyncServiceExample;
    private final ApplicationContext applicationContext;

    public AsyncController(AsyncServiceExample asyncServiceExample, ApplicationContext applicationContext) {
        this.asyncServiceExample = asyncServiceExample;
        this.applicationContext = applicationContext;
    }

    @GetMapping("/async")
    public String asyncMethod() throws ExecutionException, InterruptedException {
        return asyncServiceExample.asyncMethod().get();
    }

    @GetMapping("/executer-info")
    public ThreadPoolInfo getThreadPoolInfo(Model model) {
        ThreadPoolTaskExecutor executor = applicationContext.getBean(ThreadPoolTaskExecutor.class);

        int activeThreads = executor.getActiveCount();
        int corePoolSize = executor.getCorePoolSize();
        int maxPoolSize = executor.getMaxPoolSize();
        int keepAliveTime = executor.getKeepAliveSeconds();
        int queueSize = executor.getThreadPoolExecutor().getQueue().size();
        int queueCapacity = executor.getThreadPoolExecutor().getQueue().remainingCapacity();

        return new ThreadPoolInfo(activeThreads, corePoolSize, maxPoolSize,
                keepAliveTime, queueSize, queueCapacity);

    }
}
