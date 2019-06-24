package com.northcity.blog.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
public class RedisConfig {
  private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);
  @Value("${spring.redis.jedis.pool.max-idle}")
  int maxIdle;
  @Value("${spring.redis.jedis.pool.min-idle}")
  int minIdle;
  @Value("${spring.redis.jedis.pool.max-active}")
  int maxActive;
  @Value("${spring.redis.jedis.pool.max-wait}")
  int maxWaitMillis;

  @Bean
  @SuppressWarnings("all")
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

    RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();

    template.setConnectionFactory(factory);

    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer =
        new Jackson2JsonRedisSerializer(Object.class);

    ObjectMapper om = new ObjectMapper();

    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

    jackson2JsonRedisSerializer.setObjectMapper(om);

    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

    // key采用String的序列化方式

    template.setKeySerializer(stringRedisSerializer);

    // hash的key也采用String的序列化方式

    template.setHashKeySerializer(stringRedisSerializer);

    // value序列化方式采用jackson

    template.setValueSerializer(jackson2JsonRedisSerializer);

    // hash的value序列化方式采用jackson
    template.setHashValueSerializer(jackson2JsonRedisSerializer);
    template.afterPropertiesSet();
    return template;
  }
//  // 使用jedis连接池建立jedis连接工厂
//  @Bean
//  public JedisConnectionFactory jedisConnectionFactory() {
//    logger.info("jedisConnectionFactory:初始化了");
//    JedisPoolConfig config = new JedisPoolConfig();
//    config.setMaxIdle(maxIdle);
//    config.setMinIdle(minIdle);
//    config.setMaxWaitMillis(maxWaitMillis);
//    config.setMaxTotal(maxActive);
//    // 链接耗尽时是否阻塞，默认true
//    config.setBlockWhenExhausted(true);
//    // 是否启用pool的jmx管理功能，默认true
//    config.setJmxEnabled(true);
//    return new JedisConnectionFactory();
//  }
}
