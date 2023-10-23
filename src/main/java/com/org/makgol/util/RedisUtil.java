package com.org.makgol.util;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;


@Service
public class RedisUtil {
 
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	

	public String getData(String key) {
		// keyÎ•? ?Üµ?ï¥ value Î¶¨ÌÑ¥
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		return valueOperations.get(key);
		
	}
	
		public void setData(String key, String value) {
			ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
			valueOperations.set(key, value);
			
		}

	// ?ú†?ö® ?ãúÍ∞? ?èô?ïà (key, value) ???û•
	public Boolean setDataExpire(String key, String value, long duration) {
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		Duration expireDuration = Duration.ofSeconds(duration);
		valueOperations.set(key, value, expireDuration);
		return true;
	}
	
	// ?Ç≠?†ú
	public void deleteData(String key) {
		redisTemplate.delete(key);
		
	}
}
