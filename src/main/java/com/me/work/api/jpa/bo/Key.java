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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;

@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
@Data
@Table(name="KEY")
@Entity
public class Key {

	@Id
	@Column(name="KEY_ID")
	@SequenceGenerator(name="KEY_SEQ_GEN", sequenceName="KEY_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(generator="KEY_SEQ_GEN", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column
	private String name;
	
	@lombok.EqualsAndHashCode.Exclude
	@lombok.ToString.Exclude
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="FK_SPELL_ID")
	private Spell spell;
	
	public static enum KeyEnum {
		
		A,Z,E,R;
		
		@Override
		public String toString() {
			return this.name();
		}
	}
}