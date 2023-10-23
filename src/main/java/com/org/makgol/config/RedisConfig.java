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
	
	// Redis ?λ²μ μ»€λ§¨?λ₯? ???κΈ? ?? hight-level μΆμ? ? κ³?, Redis ?λ²μ ?°?΄?° CRUDλ₯? ?? interface ? κ³?
	//RedisTemplate?? Redis ?¬?©? μ€μ? ?­? ? ?©??€. ? ?¬ λ°μ? ?λ°? κ°μ²΄λ₯? serialization/deserialization κΈ°λ₯? ?΅?΄ key,value ??λ‘? Redis λ©λͺ¨λ¦¬μ ???₯?κ±°λ κ°?? Έ?΅??€.
	@Bean
	public RedisTemplate<?, ?> redisTemplate(){
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return redisTemplate;
	}	 	
}

