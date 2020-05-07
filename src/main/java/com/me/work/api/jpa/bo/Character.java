package com.me.work.api.jpa.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Table(name="CHARACTER")
@Entity
public class Character implements Serializable {

	@Id
	@Column(name="CHARACTER_ID")
	@SequenceGenerator(name="CHARACTER_SEQ_GEN", sequenceName="CHARACTER_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(generator="CHARACTER_SEQ_GEN", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@lombok.EqualsAndHashCode.Exclude
	@Column(name="CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@lombok.EqualsAndHashCode.Exclude
	@OneToMany(mappedBy="character",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Role> roles;

	@lombok.EqualsAndHashCode.Exclude
	@OneToMany(mappedBy="character", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Life> lifes;
	
	@lombok.EqualsAndHashCode.Exclude
	@OneToMany(mappedBy="character", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Spell> spells;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * @return list of {@link Role}
	 */
	public List<Role> getRoles() {
		
		if(this.roles == null)
			this.roles = new ArrayList<>();
		
		return this.roles;
	}
	
	/**
	 * @return list of {@link Life}
	 */
	public List<Life> getLifes() {
		
		if(this.lifes == null)
			this.lifes = new ArrayList<>();
		
		return this.lifes;
	}
	
	/**
	 * @return list of {@link Spell}
	 */
	public List<Spell> getSpells() {
		
		if(this.spells == null)
			this.spells = new ArrayList<>();
		
		return this.spells;
	}
	
	public static enum CharacterNameEnum {
		
		LEORIC, JAINA, CASSIA, RAYNOR;
		
		@Override
		public String toString() {
			return this.name();
		}
	}
}