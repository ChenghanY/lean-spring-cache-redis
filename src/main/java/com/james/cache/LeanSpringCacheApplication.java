package com.james.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;

@SpringBootApplication
@EnableCaching
public class LeanSpringCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeanSpringCacheApplication.class, args);
	}

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("192.168.208.129", 6379);
		return new JedisConnectionFactory(config);
	}

	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		StringRedisSerializer serializer = new StringRedisSerializer();
		RedisCacheConfiguration defaultConfig = defaultCacheConfig();

		defaultConfig = defaultConfig
                // 指定序列化方式防止乱码
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer))
                // cacheName:key 组织缓存名，可以用可视化工具比较好的观察结果
				.computePrefixWith(cacheName -> cacheName + ":");

		return RedisCacheManager.builder(connectionFactory)
				.cacheDefaults(defaultConfig)
				.build();
	}
}
