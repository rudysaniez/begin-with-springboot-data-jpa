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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="FK_ROLE_ID")
	private Role role;

	@lombok.EqualsAndHashCode.Exclude
	@OneToOne(mappedBy="character", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Life life;
	
	@lombok.EqualsAndHashCode.Exclude
	@OneToMany(mappedBy="character", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Spell> spells;
	
	private static final long serialVersionUID = 1L;
	
	public List<Spell> getSpells() {
		
		if(this.spells == null)
			this.spells = new ArrayList<Spell>();
		
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