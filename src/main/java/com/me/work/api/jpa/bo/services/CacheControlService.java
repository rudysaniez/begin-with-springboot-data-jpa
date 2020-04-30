package com.me.work.api.jpa.bo.services;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@CacheConfig(cacheNames="cacheControl")
@Service
public class CacheControlService {

	/**
	 * @param control
	 * @return the control
	 */
	@Cacheable
	public String getValue(String control) {
		
		if(StringUtils.isEmpty(control))
			throw new IllegalArgumentException();
		
		return control;
	}
}