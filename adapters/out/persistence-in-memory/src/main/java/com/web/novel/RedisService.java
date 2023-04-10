package com.web.novel;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RedisService {

    private static final String KEY_PREFIX = "com.novel.web:";

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    public RedisService(final StringRedisTemplate redisTemplate, final ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public <T> Optional<T> get(String key, Class<T> type) {
        String serializedValue = redisTemplate.opsForValue().get(redisKey(key));

        try {
            return Optional.of(objectMapper.readValue(serializedValue, type));
        } catch (Exception e) {
            log.error("", e);
        }

        return Optional.empty();
    }

    public void set(final String key, Object value) {
        try {
            String serializedValue = objectMapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(redisKey(key), serializedValue);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private String redisKey(final String key) {
        return KEY_PREFIX + key;
    }
}
