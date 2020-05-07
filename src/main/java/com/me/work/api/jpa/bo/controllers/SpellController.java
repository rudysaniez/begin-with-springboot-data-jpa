package com.me.work.api.jpa.bo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.me.work.api.Api;
import com.me.work.api.jpa.mapper.SpellMapper;
import com.me.work.api.jpa.repository.SpellRepository;
import com.me.work.character.v1.Spell;

@RepositoryRestController
public class SpellController {

	@Autowired
	private SpellRepository spellRepository;
	
	@Autowired
	private SpellMapper spellMapper;
	
	/**
	 * @param id
	 * @return {@link Spell}
	 */
	@GetMapping(value=Api.SPELL_PATH + "/{id}")
	public ResponseEntity<EntityModel<Spell>> getOne(@PathVariable(name="id", required=true) Long id) {
		
		Optional<com.me.work.api.jpa.bo.Spell> optionalSpell = this.spellRepository.findById(id);
		
		if(optionalSpell.isPresent()) 
			return ResponseEntity.ok(new EntityModel<Spell>(this.spellMapper.toModel(optionalSpell.get())));
		
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * @param page
	 * @param assembler
	 * @return page of {@link Spell}
	 */
	public ResponseEntity<PagedModel<EntityModel<Spell>>> getAll(Pageable page, PagedResourcesAssembler<Spell> assembler) {
	
		Page<com.me.work.api.jpa.bo.Spell> pageOfSpells = this.spellRepository.findAll(page);
		
		if(pageOfSpells.hasContent())
			return ResponseEntity.status(HttpStatus.FOUND).body(assembler.toModel(pageOfSpells.map(this.spellMapper::toModel)));
		
		return ResponseEntity.notFound().build();
	}
}