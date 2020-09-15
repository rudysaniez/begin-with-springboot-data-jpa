package com.me.work.api.jpa.bo;

import java.io.Serializable;
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
@Table(name="ROLE", schema="characterdb", catalog="characterdb")
public class Role implements Serializable {

	@Id
	@Column(name="ROLE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NAME")
	@NotEmpty private String name;
	
	@lombok.EqualsAndHashCode.Exclude
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATION_DATE")
	@NotNull private Date creationDate;
	
	@lombok.ToString.Exclude
	@lombok.EqualsAndHashCode.Exclude
	@OneToMany(mappedBy="role", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Character> character;
	
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