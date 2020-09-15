package com.me.work.api;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.me.work.api.jpa.bo.Character;
import com.me.work.api.jpa.bo.Key;
import com.me.work.api.jpa.bo.Life;
import com.me.work.api.jpa.bo.Range;
import com.me.work.api.jpa.bo.Role;
import com.me.work.api.jpa.bo.Spell;
import com.me.work.api.jpa.repository.CharacterRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Initialization implements ApplicationRunner {

	@Autowired private CharacterRepository characterRepository;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		log.info(" > Jpa application start up...");
		
		Optional<Character> optOfCharacter = characterRepository.findByName("JAINA");
		
		if(!optOfCharacter.isPresent()) {
		
			//Initialization
			
			//Role
			Role role = new Role();
			role.setCreationDate(new Date());
			role.setName(Role.RoleNameEnum.REMOTE_ASSASSIN.name());
			
			//Character
			com.me.work.api.jpa.bo.Character character = new com.me.work.api.jpa.bo.Character();
			character.setCreationDate(new Date());
			character.setName(Character.CharacterNameEnum.JAINA.name());
			character.setName(Character.CharacterNameEnum.JAINA.name());
			
			//KeyA
			Key keyA = new Key();
			keyA.setName(Key.KeyEnum.A.name());
			keyA.setCreateDate(new Date());
			
			//Spell Z
			Key keyZ = new Key();
			keyZ.setName(Key.KeyEnum.Z.name());
			keyZ.setCreateDate(new Date());
			
			//Life
			Life life = new Life();
			life.setMinimumLife(1400);
			life.setUpByLevel(4);
			life.setCreateDate(new Date());
			life.setCharacter(character);
			character.setLife(life);
			
			character.setRole(role);
			
			//Spell A
			Spell spellA = new Spell();
			spellA.setBasicDamage(80);
			spellA.setUpByLevel(5);
			spellA.setCreationDate(new Date());
			spellA.setCharacter(character);
			spellA.setControlType(Spell.ControlTypeEnum.SLOWDOWN.name());
			spellA.setKey(keyA);
			spellA.setName(Spell.SpellNameEnum.FLASH_OF_FROST.name());
			spellA.setEffectArea(Spell.EffectAreaEnum.LINE.name()); //FIXME must be in the cache
			
			Range rangeSpellA = new Range();
			rangeSpellA.setCreateDate(new Date());
			rangeSpellA.setShootingRange(7d);
			rangeSpellA.setSpell(spellA);
			spellA.setRange(rangeSpellA);
			
			character.getSpells().add(spellA);
	
			Spell spellZ = new Spell();
			spellZ.setBasicDamage(250);
			spellZ.setUpByLevel(15);
			spellZ.setIterationNumber(3);
			spellZ.setCreationDate(new Date());
			spellZ.setCharacter(character);
			spellZ.setControlType(Spell.ControlTypeEnum.SLOWDOWN.name());
			spellZ.setKey(keyZ);
			spellZ.setName(Spell.SpellNameEnum.BLIZZARD.name());
			spellZ.setEffectArea(Spell.EffectAreaEnum.CIRCLE.name()); //FIXME must be in the cache
			
			Range rangeSpellZ = new Range();
			rangeSpellZ.setCreateDate(new Date());
			rangeSpellZ.setShootingRange(5d);
			rangeSpellZ.setSpell(spellZ);
			
			spellZ.setRange(rangeSpellZ);
			
			character.getSpells().add(spellZ);
			
			characterRepository.save(character);
		}
		else {
			if(log.isInfoEnabled()) log.info(" > JAINA already exists.");
		}
	}
}
