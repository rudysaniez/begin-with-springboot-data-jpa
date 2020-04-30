package com.me.work.character.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;

@Data
public class Spell {

	@JsonProperty(value="id", required=false)
	private Long id;
	
	@JsonProperty(value="name", required=true)
	private String name;
	
	@Exclude
	@JsonProperty(value="controlType", required=true)
	private String controlType;
	
	@Exclude
	@JsonProperty(value="basicDamage", required=true)
	private int basicDamage;
	
	@Exclude
	@JsonProperty(value="upByLevel", required=true)
	private int upByLevelPct;
	
	@Exclude
	@JsonProperty(value="iterationNumber", required=true)
	private int iterationNumber = 1;
	
	@JsonProperty(value="key", required=true)
	private Key key;
}