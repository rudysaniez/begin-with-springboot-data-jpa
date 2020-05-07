package com.me.work.api.jpa.bo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="LIFE")
@Entity
public class Life implements Serializable {

	@Id
	@Column(name="LIFE_ID")
	@SequenceGenerator(name="LIFE_SEQ_GEN", sequenceName="LIFE_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(generator="LIFE_SEQ_GEN", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="MINIMUM_LIFE")
	private Integer minimumLife;
	
	@lombok.EqualsAndHashCode.Exclude
	@Column(name="UP_BY_LEVEL_IN_PCT")
	private Integer upByLevelInPct;
	
	@lombok.EqualsAndHashCode.Exclude
	@lombok.ToString.Exclude
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="FK_CHARACTER_ID")
	private Character character;
	
	private static final long serialVersionUID = 1L;
}