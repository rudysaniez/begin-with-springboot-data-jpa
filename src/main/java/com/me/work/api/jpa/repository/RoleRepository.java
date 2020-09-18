package com.me.work.api.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.work.api.jpa.bo.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	/**
	 * @param name
	 * @return optional of {@link Role}
	 */
	public Optional<Role> findByName(String name);
}
