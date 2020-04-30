package com.me.work.api.jpa.mapper;

import org.mapstruct.Mapper;

@Mapper(uses= {DateMapper.class})
public interface CharacterMapper {

	/**
	 * @param character
	 * @return {@link com.me.work.character.v1.Character}
	 */
	public com.me.work.character.v1.Character toModel(com.me.work.api.jpa.bo.Character character);
	
	/**
	 * @param character
	 * @return {@link com.me.work.api.jpa.bo.Character}
	 */
	public com.me.work.api.jpa.bo.Character toBussinessObject(com.me.work.character.v1.Character character);
}