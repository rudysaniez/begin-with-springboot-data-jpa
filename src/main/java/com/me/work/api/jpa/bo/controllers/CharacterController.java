package com.me.work.api.jpa.bo.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.me.work.api.Api;
import com.me.work.api.jpa.mapper.CharacterMapper;
import com.me.work.api.jpa.repository.CharacterRepository;
import com.me.work.character.v1.Character;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RepositoryRestController
public class CharacterController {

	@Autowired
	private CharacterRepository characterRepository;
	
	@Autowired
	private CharacterMapper characterMapper;
	
	/**
	 * @param assembler
	 * @param pageable
	 * @return page of {@link Character}
	 */
	@GetMapping(value=Api.CHARACTER_PATH)
	public ResponseEntity<PagedModel<EntityModel<Character>>> getAll(PagedResourcesAssembler<Character> assembler, Pageable pageable) {
	
		Page<com.me.work.api.jpa.bo.Character> page = this.characterRepository.findAll(pageable);
		
		if(page != null && !page.isEmpty()) {
			
			PagedModel<EntityModel<Character>> pagedModel = assembler.toModel(page.map(characterMapper::toModel));
			
			return ResponseEntity.ok(pagedModel);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * @param id
	 * @return {@link Character}
	 */
	@GetMapping(value=Api.CHARACTER_PATH + "/{id}")
	public ResponseEntity<EntityModel<Character>> getOne(@PathVariable(name="id", required=true) Long id) {

		Optional<com.me.work.api.jpa.bo.Character> character = this.characterRepository.findById(id);
		if(character.isPresent())
			return ResponseEntity.ok(new EntityModel<Character>(this.characterMapper.toModel(character.get())));
		
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * @param in
	 * @return {@link Character}
	 */
	@PostMapping(value=Api.CHARACTER_PATH)
	public ResponseEntity<EntityModel<Character>> save(@RequestBody @Valid Character in) {
		
		Page<com.me.work.api.jpa.bo.Character> pageOfCharacter = this.characterRepository.findByName(in.getName(), PageRequest.of(0, 1));
		
		if(pageOfCharacter.isEmpty())
			return ResponseEntity.status(HttpStatus.CREATED).
					body(new EntityModel<Character>(this.characterMapper.toModel(this.characterCreation(in))));
		else
			return ResponseEntity.badRequest().build();
	}
	
	/**
	 * @param in
	 * @return {@link com.me.work.api.jpa.bo.Character}
	 */
	private com.me.work.api.jpa.bo.Character characterCreation(Character in) {
		
		com.me.work.api.jpa.bo.Character characterBo = this.characterMapper.toBussinessObject(in);
		characterBo = this.completeJpaBiDirectional(characterBo);
		characterBo = this.allIdToNull(characterBo);
		
		if(log.isInfoEnabled())
			log.info(" > Character must be saved : " + characterBo);
		
		//Creation of Character
		characterBo = this.characterRepository.save(characterBo);
		
		if(log.isInfoEnabled())
			log.info(" > Character after saved : " + characterBo);
		
		return characterBo;
	}
	
	/**
	 * @param in
	 * @return {@link com.me.work.api.jpa.bo.Character}
	 */
	private com.me.work.api.jpa.bo.Character completeJpaBiDirectional(com.me.work.api.jpa.bo.Character in) {
		
		if(in == null)
			throw new IllegalArgumentException();
		
		in.getSpells().forEach(s -> s.setCharacter(in));
		in.getRoles().forEach(r -> r.setCharacter(in));
		in.getLifes().forEach(l -> l.setCharacter(in));
		in.getSpells().forEach(s -> s.getKeys().forEach(k -> k.setSpell(s)));
		in.getSpells().forEach(s -> s.getRanges().forEach(r -> r.setSpell(s)));
		
		return in;
	}
	
	/**
	 * @param in
	 * @return {@link com.me.work.api.jpa.bo.Character}
	 */
	private com.me.work.api.jpa.bo.Character allIdToNull(com.me.work.api.jpa.bo.Character in) {
		
		
		if(in == null)
			throw new IllegalArgumentException();
		
		in.setId(null);
		in.getSpells().forEach(s -> {s.setId(null);s.getKeys().forEach(k -> k.setId(null));
										s.getRanges().forEach(r -> r.setId(null));
			});
		in.getLifes().forEach(l -> l.setId(null));
		in.getRoles().forEach(r -> r.setId(null));
		
		return in;
	}
}