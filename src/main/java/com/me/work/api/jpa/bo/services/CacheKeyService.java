package com.me.work.api.jpa.bo.services;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@CacheConfig(cacheNames="cacheKey")
@Service
public class CacheKeyService {

	/**
	 * @param key
	 * @return the key
	 */
	@Cacheable
	public String getValue(String key) {
		
		if(StringUtils.isEmpty(key))
			throw new IllegalArgumentException();
		
		return key;
	}
}