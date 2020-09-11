package com.me.work.api.jpa.bo.controllers;

import java.util.Arrays;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.me.work.api.Application.ApiPaginationInformation;
import com.me.work.api.jpa.mapper.CharacterMapper;
import com.me.work.api.jpa.mapper.PageMetadataMapper;
import com.me.work.api.jpa.repository.CharacterRepository;
import com.me.work.character.api.CharactersApi;
import com.me.work.character.api.model.Character;
import com.me.work.character.api.model.PagedCharacter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RepositoryRestController
public class CharacterControllerImpl implements CharactersApi {
	
	@Autowired private CharacterRepository characterRepository;
	@Autowired private CharacterMapper mapper;
	@Autowired private PageMetadataMapper pageMapper;
	@Autowired private ApiPaginationInformation pagination;
	@Autowired private PagedResourcesAssembler<Character> assembler;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<PagedCharacter> findCharacters(@Valid String name, @Valid Integer pageNumber, @Valid Integer pageSize) {
		
		if(pageNumber == null) pageNumber = pagination.getDefaultPageNumber();
		if(pageSize == null) pageSize = pagination.getDefaultPageSize();
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		if(StringUtils.isEmpty(name)) {
			
			Page<com.me.work.api.jpa.bo.Character> pageOfCharacter = characterRepository.findAll(pageable);
			
			if(!pageOfCharacter.isEmpty()) {
				
				Page<Character> pageOfCharacterModel = pageOfCharacter.map(mapper::toModel);
				
				PagedModel<EntityModel<Character>> pagedModelOfCharacter = assembler.toModel(pageOfCharacterModel);
				
				return ResponseEntity.ok(toPagedCharacter(pagedModelOfCharacter));
			}
			
		} else {
			
			Optional<com.me.work.api.jpa.bo.Character> optionalOfCharacter = characterRepository.findByName(name);
			
			if(optionalOfCharacter.isPresent()) {
				
				Page<Character> pageOfCharacterModel = new PageImpl<Character>(Arrays.asList(mapper.toModel(optionalOfCharacter.get())), 
						pageable, 1);
				
				PagedModel<EntityModel<Character>> pagedModelOfCharacter = assembler.toModel(pageOfCharacterModel);
				
				return ResponseEntity.ok(toPagedCharacter(pagedModelOfCharacter));
			}
			else return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.notFound().build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<com.me.work.character.api.model.Character> getCharacter(String id) {
		
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<com.me.work.character.api.model.Character> saveCharacter(@Valid com.me.work.character.api.model.Character character) {

		return null;
	}
	
	/**
	 * @param in
	 * @return {@link PagedCharacter}
	 */
	private PagedCharacter toPagedCharacter(PagedModel<EntityModel<Character>> in) {
		
		PagedCharacter paged = new PagedCharacter();
		
		in.getContent().forEach(entity -> paged.addContentItem(entity.getContent()));
		paged.setPage(pageMapper.toModel(in.getMetadata()));
		
		return paged;
	}
}
