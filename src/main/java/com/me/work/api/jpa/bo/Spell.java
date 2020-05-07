package com.me.work.api.jpa.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

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
	
	@lombok.EqualsAndHashCode.Exclude
	@Column(name="TYPE")
	private String controlType;
	
	@lombok.EqualsAndHashCode.Exclude
	@Column(name="BASIC_DAMAGE")
	private int basicDamage;
	
	@lombok.EqualsAndHashCode.Exclude
	@Column(name="UP_BY_LEVEL_PCT")
	private int upByLevelPct;
	
	@lombok.EqualsAndHashCode.Exclude
	@Column(name="ITERATION_NUMBER")
	private int iterationNumber = 1;
	
	@lombok.EqualsAndHashCode.Exclude
	@Column(name="EFFECT_AREA")
	private String effectArea;
	
	@lombok.EqualsAndHashCode.Exclude
	@lombok.ToString.Exclude
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="FK_CHARACTER_ID")
	private Character character;
	
	@lombok.EqualsAndHashCode.Exclude
	@OneToMany(mappedBy="spell", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Key> keys;
	
	@lombok.EqualsAndHashCode.Exclude
	@OneToMany(mappedBy="spell", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Range> ranges;
	
	/**
	 * @return list of {@link Key}
	 */
	public List<Key> getKeys() {
		
		if(this.keys == null)
			this.keys = new ArrayList<>();
		
		return this.keys;
	}
	
	/**
	 * @return list of {@link Range}
	 */ 
	public List<Range> getRanges() {
		
		if(this.ranges == null)
			this.ranges = new ArrayList<>();
		
		return this.ranges;
	}
	
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
	
	public static enum EffectAreaEnum {
		
		CIRCLE,
		SEMI_CIRCLE,
		LINE,
		HAND_TO_HAND;
		
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