package com.me.work.character.v1;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;

@Data
public class Role {

	@JsonProperty(value="id", required=false)
	private Long id;

	@JsonProperty(value="name", required=true)
	private String name;

	@Exclude
	@JsonProperty(value="creationDate", required=true)
	private OffsetDateTime creationDate;
}