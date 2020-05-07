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
@Entity
@Table(name="ROLE")
public class Role implements Serializable {

	@Id
	@Column(name="ROLE_ID")
	@SequenceGenerator(name="ROLE_SEQ_GEN", sequenceName="ROLE_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(generator="ROLE_SEQ_GEN", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@lombok.EqualsAndHashCode.Exclude
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATION_DATE")
	private Date creationDate;
	
	@lombok.ToString.Exclude
	@lombok.EqualsAndHashCode.Exclude
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="FK_CHARACTER_ID")
	private Character character;
	
	private static final long serialVersionUID = 1L;
	
	public static enum RoleNameEnum {
		
		OFFENSIVE_WARRIOR,
		REMOTE_ASSASSIN;
		
		@Override
		public String toString() {
			return this.name();
		}
	}
}