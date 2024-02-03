package org.omidmohebbise.async.controller.dto;

public record ThreadPoolInfo(
        int activeThreads,
        int corePoolSize,
        int maxPoolSize,
        int keepAliveTime,
        int queueSize,
        int queueCapacity
) {}
