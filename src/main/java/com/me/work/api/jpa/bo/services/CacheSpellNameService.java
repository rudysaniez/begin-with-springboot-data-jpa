package com.me.work.api.jpa.bo.services;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@CacheConfig(cacheNames="cacheSpellName")
@Service
public class CacheSpellNameService {

	/**
	 * @param spellName
	 * @return spell name
	 */
	@Cacheable
	public String getValue(String spellName) {
		
		if(StringUtils.isEmpty(spellName))
			throw new IllegalArgumentException();
		
		return spellName;
	}
}