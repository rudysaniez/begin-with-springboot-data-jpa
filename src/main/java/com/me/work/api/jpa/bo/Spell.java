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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Validated
@NoArgsConstructor
@Data
@Entity
@Table(name="SPELL", schema="characterdb", catalog="characterdb")
public class Spell implements Serializable {

	@Id
	@Column(name="SPELL_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NAME")
	@NotEmpty private String name;
	
	@lombok.EqualsAndHashCode.Exclude
	@Column(name="TYPE")
	@NotEmpty private String controlType;
	
	@lombok.EqualsAndHashCode.Exclude
	@Column(name="BASIC_DAMAGE")
	@NotNull private Integer basicDamage;
	
	@lombok.EqualsAndHashCode.Exclude
	@Column(name="UP_BY_LEVEL")
	@NotNull private Integer upByLevel;
	
	@lombok.EqualsAndHashCode.Exclude
	@Column(name="ITERATION_NUMBER")
	@NotNull private Integer iterationNumber = 1;
	
	@lombok.EqualsAndHashCode.Exclude
	@Column(name="EFFECT_AREA")
	@NotEmpty private String effectArea;
	
	@lombok.EqualsAndHashCode.Exclude
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATION_DATE")
	@NotNull private Date creationDate;
	
	@lombok.EqualsAndHashCode.Exclude
	@lombok.ToString.Exclude
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="FK_CHARACTER_ID")
	private Character character;
	
	@lombok.EqualsAndHashCode.Exclude
	@lombok.ToString.Exclude
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="FK_KEY_ID")
	private Key key;
	
	@lombok.EqualsAndHashCode.Exclude
	@lombok.ToString.Exclude
	@OneToOne(mappedBy="spell", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Range range;
	
	private static final long serialVersionUID = 1L;
	
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