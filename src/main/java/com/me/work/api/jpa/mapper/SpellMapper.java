package com.me.work.api.jpa.mapper;

import org.mapstruct.Mapper;

@Mapper(uses= {DateMapper.class})
public interface SpellMapper {

	/**
	 * @param spell
	 * @return {@link com.me.work.character.v1.Spell}
	 */
	public com.me.work.character.v1.Spell toModel(com.me.work.api.jpa.bo.Spell spell);
	
	/**
	 * @return {@link com.me.work.api.jpa.bo.Spell}
	 */
	public com.me.work.api.jpa.bo.Spell toBusinessObject(com.me.work.character.v1.Spell spell);
}