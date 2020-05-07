package com.me.work.character.v1;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Spell {

	@JsonProperty(value="id", required=false)
	private Long id;
	
	@JsonProperty(value="name", required=true)
	private String name;
	
	@lombok.EqualsAndHashCode.Exclude
	@JsonProperty(value="controlType", required=true)
	private String controlType;
	
	@lombok.EqualsAndHashCode.Exclude
	@JsonProperty(value="basicDamage", required=true)
	private int basicDamage;
	
	@lombok.EqualsAndHashCode.Exclude
	@JsonProperty(value="upByLevel", required=true)
	private int upByLevelPct;
	
	@lombok.EqualsAndHashCode.Exclude
	@JsonProperty(value="iterationNumber", required=true)
	private int iterationNumber = 1;
	
	@lombok.EqualsAndHashCode.Exclude
	@JsonProperty(value="effectArea", required=true)
	private String effectArea;
	
	@JsonProperty(value="keys", required=true)
	private List<Key> keys = new ArrayList<>();
	
	@JsonProperty(value="ranges", required=true)
	private List<Range> ranges = new ArrayList<>();
}