package com.me.work.api.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.work.api.jpa.bo.Key;

public interface KeyRepository extends JpaRepository<Key, Long> {

	/**
	 * @param name
	 * @return optional of {@link Key}
	 */
	Optional<Key> findByName(String name);
}
