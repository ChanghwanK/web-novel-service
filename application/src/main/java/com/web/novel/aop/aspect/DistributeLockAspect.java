package com.web.novel.aop.aspect;

import com.web.novel.aop.annotation.DistributeLock;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class DistributeLockAspect {

    private static final String REDISSON_KEY_PREFIX = "RLOCK:web-novel:";
    private final RedissonClient redissonClient;

    public DistributeLockAspect(
            final RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Pointcut("@annotation(com.web.novel.aop.annotation.DistributeLock)")
    public void pointCut() { }

    @Around("pointCut()")
    public void distributeLock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributeLock distributeLock = method.getAnnotation(DistributeLock.class);

        RLock lock = redissonClient.getLock(REDISSON_KEY_PREFIX);

        try {
            lock.tryLock(distributeLock.waitTime(), distributeLock.leaseTime(), distributeLock.timeUnit());    // (4)
            if (! lock.isLocked()) {
                return ;
            }
            joinPoint.proceed();
        } catch (InterruptedException ex) {
            log.error("{}", ex);
            throw new InterruptedException();
        } finally {
            lock.unlock();
        }
    }
}
