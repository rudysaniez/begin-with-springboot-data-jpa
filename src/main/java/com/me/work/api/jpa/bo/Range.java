package com.me.work.api.jpa.bo;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Table(name="RANGE")
@Entity
public class Range implements Serializable {

	@Id
	@Column(name="RANGE_ID")
	@SequenceGenerator(name="RANGE_SEQ_GEN", sequenceName="RANGE_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(generator="RANGE_SEQ", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@lombok.EqualsAndHashCode.Exclude
	@Column(name="SHOOTING_RANGE")
	private Double shootingRange;
	
	@lombok.EqualsAndHashCode.Exclude
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATION_DATE")
	private Date createDate;
	
	@lombok.EqualsAndHashCode.Exclude
	@lombok.ToString.Exclude
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="FK_SPELL_ID")
	private Spell spell;
	
	private static final long serialVersionUID = 1L;
}