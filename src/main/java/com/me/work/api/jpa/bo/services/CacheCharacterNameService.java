package com.me.work.api.jpa.bo.services;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@CacheConfig(cacheNames="cacheCharacterName")
@Service
public class CacheCharacterNameService {

	/**
	 * @param characterName
	 * @return the character name
	 */
	@Cacheable
	public String getValue(String characterName) {
		
		if(StringUtils.isEmpty(characterName))
			throw new IllegalArgumentException();
		
		return characterName;
	}
}