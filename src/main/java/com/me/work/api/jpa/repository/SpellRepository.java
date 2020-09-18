package com.me.work.api.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.me.work.api.jpa.bo.Spell;

public interface SpellRepository extends JpaRepository<Spell, Long> {

	/**
	 * @param name
	 * @param page
	 * @return page of {@link Spell}
	 */
	public Page<Spell> findByName(String name, Pageable page);
}