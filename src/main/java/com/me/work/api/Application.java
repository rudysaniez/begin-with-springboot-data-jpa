package com.me.work.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.me.work.api.jpa.bo.Character;
import com.me.work.api.jpa.bo.Key;
import com.me.work.api.jpa.bo.Life;
import com.me.work.api.jpa.bo.Range;
import com.me.work.api.jpa.bo.Role;
import com.me.work.api.jpa.bo.Spell;
import com.me.work.api.jpa.bo.services.CacheCharacterNameService;
import com.me.work.api.jpa.bo.services.CacheControlService;
import com.me.work.api.jpa.bo.services.CacheKeyService;
import com.me.work.api.jpa.bo.services.CacheRoleService;
import com.me.work.api.jpa.bo.services.CacheSpellNameService;
import com.me.work.api.jpa.mapper.CharacterMapper;
import com.me.work.api.jpa.mapper.CharacterMapperImpl;
import com.me.work.api.jpa.mapper.SpellMapper;
import com.me.work.api.jpa.mapper.SpellMapperImpl;
import com.me.work.api.jpa.repository.CharacterRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableCaching
@SpringBootApplication
public class Application implements ApplicationRunner {

	@Autowired
	CacheControlService cacheControlService;
	
	@Autowired
	CacheRoleService cacheRoleService;
	
	@Autowired
	CacheKeyService cacheKeyService;
	
	@Autowired
	CacheCharacterNameService cacheCharacterNameService;
	
	@Autowired
	CacheSpellNameService cacheSpellNameService;
	
	@Autowired
	CharacterRepository characterRepository;
	
	@Autowired
	CacheManager cacheManager;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CharacterMapper characterMapper() {
		return new CharacterMapperImpl();
	}
	
	@Bean
	public SpellMapper spellMapper() {
		return new SpellMapperImpl();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		log.info(" > Cache manager is " + this.cacheManager.getClass().getName());
		
		log.info(" > Jpa application start up...");
		
		//Initialization
		com.me.work.api.jpa.bo.Character character = new com.me.work.api.jpa.bo.Character();
		character.setCreationDate(new Date());
		character.setName(Character.CharacterNameEnum.JAINA.name());
		character.setName(this.cacheCharacterNameService.getValue(Character.CharacterNameEnum.JAINA.name()));
		
		//Life
		Life life = new Life();
		life.setMinimumLife(1400);
		life.setUpByLevelInPct(4);
		life.setCharacter(character);
		character.getLifes().add(life);
		
		Role role = new Role();
		role.setCreationDate(new Date());
		role.setName(this.cacheRoleService.getValue(Role.RoleNameEnum.REMOTE_ASSASSIN.name()));
		role.setCharacter(character);
		character.getRoles().add(role);
		
		//Spell A
		Key keyA = new Key();
		keyA.setName(this.cacheKeyService.getValue(Key.KeyEnum.A.name()));
		
		Spell spellA = new Spell();
		spellA.setBasicDamage(80);
		spellA.setUpByLevelPct(5);
		spellA.setCharacter(character);
		spellA.setControlType(this.cacheControlService.getValue(Spell.ControlTypeEnum.SLOWDOWN.name()));
		spellA.getKeys().add(keyA);
		spellA.setName(this.cacheSpellNameService.getValue(Spell.SpellNameEnum.FLASH_OF_FROST.name()));
		spellA.setEffectArea(Spell.EffectAreaEnum.LINE.name()); //FIXME must be in the cache
		
		keyA.setSpell(spellA);
		
		Range rangeSpellA = new Range();
		rangeSpellA.setCreateDate(new Date());
		rangeSpellA.setShootingRange(7d);
		rangeSpellA.setSpell(spellA);
		spellA.getRanges().add(rangeSpellA);
		
		character.getSpells().add(spellA);

		//Spell Z
		Key keyZ = new Key();
		keyZ.setName(this.cacheKeyService.getValue(Key.KeyEnum.Z.name()));
		
		Spell spellZ = new Spell();
		spellZ.setBasicDamage(250);
		spellZ.setUpByLevelPct(15);
		spellZ.setIterationNumber(3);
		spellZ.setCharacter(character);
		spellZ.setControlType(this.cacheControlService.getValue(Spell.ControlTypeEnum.SLOWDOWN.name()));
		spellZ.getKeys().add(keyZ);
		spellZ.setName(this.cacheSpellNameService.getValue(Spell.SpellNameEnum.BLIZZARD.name()));
		spellZ.setEffectArea(Spell.EffectAreaEnum.CIRCLE.name()); //FIXME must be in the cache
		
		keyZ.setSpell(spellZ);
		
		Range rangeSpellZ = new Range();
		rangeSpellZ.setCreateDate(new Date());
		rangeSpellZ.setShootingRange(5d);
		rangeSpellZ.setSpell(spellZ);
		
		spellZ.getRanges().add(rangeSpellZ);
		
		character.getSpells().add(spellZ);
		
		this.characterRepository.save(character);
	}
}