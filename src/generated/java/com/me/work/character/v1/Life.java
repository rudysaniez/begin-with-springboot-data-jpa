package com.me.work.character.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Life {

	@JsonProperty(value="id", required=false)
	private Long id;
	
	@JsonProperty(value="minimumLife", required=true)
	private Integer minimumLife;
	
	@lombok.EqualsAndHashCode.Exclude
	@JsonProperty(value="upByLevelInPct", required=true)
	private Integer upByLevelInPct;
}