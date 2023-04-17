package com.web.novel;

import com.web.novel.annotations.PersistenceAdapter;
import com.web.novel.exception.DuplicatedPointNumberException;
import com.web.novel.point.PointNumber;
import com.web.novel.point.port.out.PointCacheCheckPort;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@PersistenceAdapter
public class PointCacheAdapter implements PointCacheCheckPort {

    private final RedisService redisService;

    public PointCacheAdapter(final RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public void checkDuplication(PointNumber pointNumber) {
        var key = String.valueOf(pointNumber.getValue());

        Optional<String> res = redisService.get(key, String.class);

        if (res.isPresent())
            throw new DuplicatedPointNumberException(key);
    }

    @Override
    public void setPointNumber(final PointNumber pointNumber) {
        var now = LocalDateTime.now();
        var expiresAt = now.plusMinutes(1);
        var ttl =  Duration.between(now, expiresAt);
        redisService.setIfAbsent(String.valueOf(pointNumber.getValue()), "", ttl);
    }
}
