package com.org.makgol.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName("127.0.0.1");
		redisStandaloneConfiguration.setPort(6379);
		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
		return lettuceConnectionFactory;
	}
	
	// Redis ?„œë²„ì— ì»¤ë§¨?“œë¥? ?ˆ˜?–‰?•˜ê¸? ?œ„?•œ hight-level ì¶”ìƒ?™” ? œê³?, Redis ?„œë²„ì— ?°?´?„° CRUDë¥? ?œ„?•œ interface ? œê³?
	//RedisTemplate?? Redis ?‚¬?š©?— ì¤‘ìš”?•œ ?—­?• ?„ ?•©?‹ˆ?‹¤. ? „?‹¬ ë°›ì? ?ë°? ê°ì²´ë¥? serialization/deserialization ê¸°ëŠ¥?„ ?†µ?•´ key,value ?˜•?ƒœë¡? Redis ë©”ëª¨ë¦¬ì— ???¥?•˜ê±°ë‚˜ ê°?? ¸?˜µ?‹ˆ?‹¤.
	@Bean
	public RedisTemplate<?, ?> redisTemplate(){
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return redisTemplate;
	}	 	
}

