package com.me.work.character.v1;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Character {

	@JsonProperty(value="id", required=false)
	private Long id;

	@JsonProperty(value="name", required=true)
	private String name;

	@lombok.EqualsAndHashCode.Exclude
	@JsonProperty(value="creationDate", required=false)
	private OffsetDateTime creationDate;
	
	@lombok.EqualsAndHashCode.Exclude
	@JsonProperty(value="role", required=true)
	private Role role;

	@lombok.EqualsAndHashCode.Exclude
	@JsonProperty(value="life", required=true)
	private Life life;

	@lombok.EqualsAndHashCode.Exclude @Valid
	@JsonProperty(value="spells", required=true)
	private List<Spell> spells;
	
	/**
	 * @return list of {@link Spell}
	 */
	public List<Spell> getSpells() {
		
		if(this.spells == null)
			this.spells = new ArrayList<Spell>();
		
		return this.spells;
	}
}