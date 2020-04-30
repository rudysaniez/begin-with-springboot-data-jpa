package com.me.work.api.jpa.bo.services;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@CacheConfig(cacheNames="cacheRole")
@Service
public class CacheRoleService {

	/**
	 * @param roleName
	 * @return the role name
	 */
	@Cacheable
	public String getValue(String roleName) {
		
		if(StringUtils.isEmpty(roleName))
			new IllegalArgumentException();
		
		return roleName;
	}
}