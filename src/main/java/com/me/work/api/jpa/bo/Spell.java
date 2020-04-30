package com.me.work.api.jpa.bo;

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
import lombok.EqualsAndHashCode.Exclude;

@Data
@Table(name="SPELL")
@Entity
public class Spell {

	@Id
	@Column(name="SPELL_ID")
	@SequenceGenerator(name="SPELL_SEQ_GEN", sequenceName="SPELL_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(generator="SPELL_SEQ_GEN", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Exclude
	@Column(name="TYPE")
	private String controlType;
	
	@Exclude
	@Column(name="BASIC_DAMAGE")
	private int basicDamage;
	
	@Exclude
	@Column(name="UP_BY_LEVEL_PCT")
	private int upByLevelPct;
	
	@Exclude
	@Column(name="ITERATION_NUMBER")
	private int iterationNumber = 1;
	
	@lombok.ToString.Exclude
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="FK_CHARACTER_ID")
	private Character character;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="FK_KEY_ID")
	private Key key;
	
	public static enum ControlTypeEnum {
		
		IMMOBILIZATION,
		STUNNING,
		SILENCE,
		SLEEPING,
		SLOWDOWN,
		ENSLAVED,
		NONE;
		
		@Override
		public String toString() {
			return this.name();
		}
	}
	
	public static enum SpellNameEnum {
		
		SKELETAL_STRIKE,
		FLASH_OF_FROST,
		BLIZZARD;
		
		@Override
		public String toString() {
			return this.name();
		}
	}
}