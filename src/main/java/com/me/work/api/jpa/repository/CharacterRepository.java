package com.me.work.api.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.me.work.api.Api;

@RepositoryRestResource(collectionResourceRel="characters", path=Api.CHARACTER_PATH)
public interface CharacterRepository extends JpaRepository<com.me.work.api.jpa.bo.Character, Long> {

	/**
	 * @param name
	 * @return optional of {@link Character}
	 */
	@RestResource(exported=false)
	public Optional<com.me.work.api.jpa.bo.Character> findByName(String name);
}