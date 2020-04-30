package com.me.work.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.work.api.jpa.bo.services.CacheKeyService;
import com.me.work.api.jpa.bo.services.CacheRoleService;
import com.me.work.api.jpa.mapper.CharacterMapper;
import com.me.work.api.jpa.repository.CharacterRepository;
import com.me.work.api.jpa.repository.SpellRepository;
import com.me.work.character.v1.Key;
import com.me.work.character.v1.Life;
import com.me.work.character.v1.Role;
import com.me.work.character.v1.Spell;

@Transactional
@Commit
@RunWith(SpringRunner.class)
@SpringBootTest
public class CharacterModelTest {

	static com.me.work.character.v1.Character CHARACTER;
	
	@Autowired
	private ObjectMapper myJack;
	
	@Autowired
	private CharacterMapper characterMapper;
	
	@Autowired
	private CharacterRepository characterRepository;
	
	@Autowired
	private SpellRepository spellRepository;
	
	@Autowired
	private CacheKeyService cacheKeyService;
	
	@Autowired
	private CacheRoleService cacheRoleService;
	
	/**
	 * Building of LEORIC character.
	 */
	@Before
	public void before() {
		
		CHARACTER = new com.me.work.character.v1.Character();
		CHARACTER.setCreationDate(OffsetDateTime.now());
		CHARACTER.setName(com.me.work.api.jpa.bo.Character.CharacterNameEnum.LEORIC.name());
		
		Key keyA = new Key();
		keyA.setName(this.cacheKeyService.getValue(com.me.work.api.jpa.bo.Key.KeyEnum.A.name()));
		
		//Spell
		Spell spell = new Spell();
		spell.setBasicDamage(120);
		spell.setControlType(com.me.work.api.jpa.bo.Spell.ControlTypeEnum.SLOWDOWN.name());
		spell.setIterationNumber(1);
		spell.setName(com.me.work.api.jpa.bo.Spell.SpellNameEnum.SKELETAL_STRIKE.name());
		spell.setUpByLevelPct(8);
		spell.setKey(keyA);
		
		CHARACTER.getSpells().add(spell);
		
		//Life
		Life life = new Life();
		life.setMinimumLife(1800);
		life.setUpByLevelInPct(4);
		
		CHARACTER.setLife(life);
		
		//Role
		Role role = new Role();
		role.setCreationDate(OffsetDateTime.now());
		role.setName(this.cacheRoleService.getValue(com.me.work.api.jpa.bo.Role.RoleNameEnum.OFFENSIVE_WARRIOR.name()));
		
		CHARACTER.setRole(role);
	}
	
	@Test
	public void test01() throws JsonProcessingException {
		
		String json = this.myJack.writeValueAsString(CHARACTER);
		assertThat(json).isNotEmpty();
		
		System.out.println(" > Json LEORIC character : " + json);
	}
	
	/**
	 * Save a character and search by name.
	 */
	@Test
	public void test02() {
		
		//Model to Bo.
		com.me.work.api.jpa.bo.Character characterBo = this.characterMapper.toBussinessObject(CHARACTER);
		assertThat(characterBo).isNotNull();
		
		//Save the bo.
		com.me.work.api.jpa.bo.Character newCharacter = this.characterRepository.save(characterBo);
		assertThat(newCharacter).isNotNull();
		
		//Search LEORIC character.
		Page<com.me.work.api.jpa.bo.Character> firstPage = this.characterRepository.findByName(com.me.work.api.jpa.bo.Character.CharacterNameEnum.LEORIC.name(), 
				PageRequest.of(0, 3));
		
		assertThat(firstPage).isNotEmpty();
		firstPage.forEach(character -> System.out.println(" > LEORIC character : " + character.toString()));
	}
	
	/**
	 * Search of Spells.
	 */
	@Test
	public void test03() {
		
		Page<com.me.work.api.jpa.bo.Spell> spells = this.spellRepository.findByName(com.me.work.api.jpa.bo.Spell.SpellNameEnum.SKELETAL_STRIKE.name(), 
				PageRequest.of(0, 10));
		
		spells.forEach(spell -> System.out.println(" > SKELETAL_STRIKE spell : " + spell.toString()));
	}
}