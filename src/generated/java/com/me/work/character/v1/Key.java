package com.me.work.character.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Key {

	@JsonProperty(value="id", required=false)
	private Long id;
	
	@JsonProperty(value="name", required=true)
	private String name;
}