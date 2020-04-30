package com.me.work.api.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.me.work.api.Api;

@RepositoryRestResource(collectionResourceRel="characters", path=Api.CHARACTER_PATH)
public interface CharacterRepository extends JpaRepository<com.me.work.api.jpa.bo.Character, Long> {

	/**
	 * @param name
	 * @param page
	 * @return page of {@link Character}
	 */
	@RestResource(exported=false)
	public Page<com.me.work.api.jpa.bo.Character> findByName(String name, Pageable page);
}