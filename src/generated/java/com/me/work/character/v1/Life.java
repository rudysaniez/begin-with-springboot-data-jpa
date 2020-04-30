package com.me.work.character.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;

@Data
public class Life {

	@JsonProperty(value="id", required=false)
	private Long id;
	
	@Exclude
	@JsonProperty(value="minimumLife", required=true)
	private Integer minimumLife;
	
	@Exclude
	@JsonProperty(value="upByLevelInPct", required=true)
	private Integer upByLevelInPct;
}