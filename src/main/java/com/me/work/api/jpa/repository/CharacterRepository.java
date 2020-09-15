package com.me.work.api.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<com.me.work.api.jpa.bo.Character, Long> {

	/**
	 * @param name
	 * @return optional of {@link Character}
	 */
	public Optional<com.me.work.api.jpa.bo.Character> findByName(String name);
}