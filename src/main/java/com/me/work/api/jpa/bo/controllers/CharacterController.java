package com.me.work.api.jpa.bo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.me.work.api.Api;
import com.me.work.api.jpa.mapper.CharacterMapper;
import com.me.work.api.jpa.repository.CharacterRepository;
import com.me.work.character.v1.Character;

@RepositoryRestController
public class CharacterController {

	@Autowired
	private CharacterRepository characterRepository;

	@Autowired
	private CharacterMapper CharacterMapper;
	
	/**
	 * @param assembler
	 * @param pageable
	 * @return page of {@link Character}
	 */
	@GetMapping(value=Api.CHARACTER_PATH)
	public ResponseEntity<PagedModel<EntityModel<Character>>> getAll(PagedResourcesAssembler<Character> assembler, Pageable pageable) {
	
		Page<com.me.work.api.jpa.bo.Character> page = this.characterRepository.findAll(pageable);
		
		if(page != null && !page.isEmpty()) {
			
			PagedModel<EntityModel<Character>> pagedModel = assembler.toModel(page.map(CharacterMapper::toModel));
			
			return ResponseEntity.ok(pagedModel);
		}
		
		return ResponseEntity.notFound().build();
	}
}