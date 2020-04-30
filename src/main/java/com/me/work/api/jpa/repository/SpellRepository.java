package com.me.work.api.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.me.work.api.jpa.bo.Spell;

@RepositoryRestResource(collectionResourceRel="spells", path="spells")
public interface SpellRepository extends JpaRepository<Spell, Long> {

	/**
	 * @param name
	 * @param page
	 * @return page of {@link Spell}
	 */
	@RestResource(exported=false)
	public Page<Spell> findByName(String name, Pageable page);
}