package com.me.work.api.jpa.bo.controllers;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.work.api.Application.ApiPaginationInformation;
import com.me.work.api.jpa.bo.Key;
import com.me.work.api.jpa.bo.Role;
import com.me.work.api.jpa.exception.AlreadyExistException;
import com.me.work.api.jpa.mapper.CharacterMapper;
import com.me.work.api.jpa.mapper.PageMetadataMapper;
import com.me.work.api.jpa.repository.CharacterRepository;
import com.me.work.api.jpa.repository.KeyRepository;
import com.me.work.api.jpa.repository.RoleRepository;
import com.me.work.character.api.CharactersApi;
import com.me.work.character.api.model.Character;
import com.me.work.character.api.model.PagedCharacter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
public class CharacterControllerImpl implements CharactersApi {
	
	@Autowired private CharacterRepository characterRepository;
	@Autowired private KeyRepository keyRepository;
	@Autowired private RoleRepository roleRepository;
	@Autowired private CharacterMapper mapper;
	@Autowired private PageMetadataMapper pageMapper;
	@Autowired private ApiPaginationInformation pagination;
	@Autowired private PagedResourcesAssembler<Character> assembler;

	/**
	 * {@inheritDoc}
	 */
	public @ResponseBody ResponseEntity<PagedCharacter> findCharacters(@Valid String name, @Valid Integer pageNumber, 
			@Valid Integer pageSize) {
		
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
	public ResponseEntity<com.me.work.character.api.model.Character> getCharacter(String id) {
		
		if(!StringUtils.isNumeric(id))
			return ResponseEntity.badRequest().build();
		
		Optional<com.me.work.api.jpa.bo.Character> optionalCharacter = this.characterRepository.findById(Long.valueOf(id));
		
		if(optionalCharacter.isPresent())
			return ResponseEntity.ok(mapper.toModel(optionalCharacter.get()));
		
		//throw new NotFoundException(String.format("The Character with id %s doesn't not exist.", id));
		return ResponseEntity.notFound().build();
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<Character> saveCharacter(@Valid Character character) {

		Optional<com.me.work.api.jpa.bo.Character> characterFound = null;
		
		if(StringUtils.isNotEmpty(character.getId()) && !StringUtils.isNumeric(character.getId()))
			return ResponseEntity.badRequest().build();
		
		//Search with id if possible.
		if(StringUtils.isNotEmpty(character.getId()))
			characterFound = this.characterRepository.findById(Long.valueOf(character.getId()));
		
		if(characterFound != null && characterFound.isPresent()) {
			
			//Update.
			characterFound.get().setName(character.getName());
			
			com.me.work.api.jpa.bo.Character characterBo = characterRepository.save(characterFound.get());
			
			if(log.isDebugEnabled())
				log.debug("The character with name {} has been updated.", characterBo.getName());
			
			return ResponseEntity.ok(mapper.toModel(characterBo));
		}
		else {
			
			Optional<com.me.work.api.jpa.bo.Character> optOfCharacter = characterRepository.findByName(character.getName());
			
			if(!optOfCharacter.isPresent()) {
				
				//Creation.
				character.setId(null);
				
				com.me.work.api.jpa.bo.Character characterToSave = mapper.toBussinessObject(character);
				setCreationDate(characterToSave);
				setBiDirectionalInformation(characterToSave);
				
				com.me.work.api.jpa.bo.Character characterBo = characterRepository.save(characterToSave);
				
				if(log.isDebugEnabled())
					log.debug("The character with name {} has been created.", characterBo.getName());
				
				return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toModel(characterBo));
			}
			else 
				throw new AlreadyExistException("This character already exist. The service is unavailable.");
		}
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

	/**
	 * @param character
	 */
	private void setCreationDate(com.me.work.api.jpa.bo.Character character) {
		
		Date now = new Date();
		
		character.setCreationDate(now);
		character.getRole().setCreationDate(now);
		character.getLife().setCreateDate(now);
		character.getSpells().forEach(s -> s.setCreationDate(now));
		character.getSpells().forEach(s -> s.getRange().setCreateDate(now));
		character.getSpells().forEach(s -> s.getKey().setCreateDate(now));
	}
	
	/**
	 * @param character
	 */
	private void setBiDirectionalInformation(com.me.work.api.jpa.bo.Character character) {
	
		Optional<Role> optOfRole = roleRepository.findByName(character.getRole().getName());
		if(optOfRole.isPresent()) character.setRole(optOfRole.get());
		
		character.getSpells().forEach(s -> s.setCharacter(character));
		
		character.getSpells().forEach(s -> {
			
			Optional<Key> optOfKey = keyRepository.findByName(s.getKey().getName());
			if(optOfKey.isPresent())
				s.setKey(optOfKey.get());
		});
		
		character.getSpells().forEach(s -> s.getRange().setSpell(s));
		
		//character.getRole().setCharacter(character);
		character.getLife().setCharacter(character);
	}
}
