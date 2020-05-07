package com.me.work.character.v1;

import java.util.Date;

import lombok.Data;

@Data
public class Range {

	private Long id;
	
	private Double shootingRange;
	
	@lombok.EqualsAndHashCode.Exclude
	private Date createDate;
}