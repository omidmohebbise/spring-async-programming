package org.omidmohebbise.async.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThreadPoolInfoController {
    private final ApplicationContext applicationContext;

    public ThreadPoolInfoController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @GetMapping("/")
    public String getThreadPoolInfo(Model model) {
        ThreadPoolTaskExecutor executor = applicationContext.getBean(ThreadPoolTaskExecutor.class);

        model.addAttribute("activeThreads", executor.getActiveCount());
        model.addAttribute("corePoolSize", executor.getCorePoolSize());
        model.addAttribute("maxPoolSize", executor.getMaxPoolSize());
        model.addAttribute("keepAliveTime", executor.getKeepAliveSeconds());
        model.addAttribute("queueSize", executor.getThreadPoolExecutor().getQueue().size());
        model.addAttribute("queueCapacity", executor.getThreadPoolExecutor().getQueue().remainingCapacity());

        return "index";
    }

    @GetMapping("/report")
    public String getThreadPoolInfo() {
        return "report";
    }
}
