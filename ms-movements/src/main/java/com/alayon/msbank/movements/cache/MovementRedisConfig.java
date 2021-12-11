package com.alayon.msbank.movements.cache;

import com.alayon.msbank.movements.models.MovementModel;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

import java.net.UnknownHostException;
import java.time.Duration;


@Configuration
public class MovementRedisConfig {

    @Bean
    public RedisTemplate<Object, MovementModel> movRedisTemplate(final RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        final RedisTemplate<Object, MovementModel> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);

        // serializador
        final Jackson2JsonRedisSerializer<MovementModel> ser = new Jackson2JsonRedisSerializer<MovementModel>(
                MovementModel.class);
        template.setDefaultSerializer(ser);
        return template;
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(1)).disableCachingNullValues()
                .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder.withCacheConfiguration("movement",
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(15)));
        // .withCacheConfiguration("movement",
        // RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(15)));
        // .withCacheConfiguration("customerCache",
        // RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)));
    }

}