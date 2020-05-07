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
	@JsonProperty(value="roles", required=true)
	private List<Role> roles = new ArrayList<>();

	@lombok.EqualsAndHashCode.Exclude
	@JsonProperty(value="lifes", required=true)
	private List<Life> lifes = new ArrayList<>();

	@lombok.EqualsAndHashCode.Exclude @Valid
	@JsonProperty(value="spells", required=true)
	private List<Spell> spells = new ArrayList<>();
	
	/**
	 * @return list of {@link Spell}
	 */
	public List<Spell> getSpells() {
		
		if(this.spells == null)
			this.spells = new ArrayList<Spell>();
		
		return this.spells;
	}
}