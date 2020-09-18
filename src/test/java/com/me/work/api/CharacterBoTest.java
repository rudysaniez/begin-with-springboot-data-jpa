package com.me.work.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.me.work.api.jpa.bo.Character;
import com.me.work.api.jpa.bo.Spell;
import com.me.work.api.jpa.repository.CharacterRepository;
import com.me.work.api.jpa.repository.SpellRepository;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.HSQL)
@RunWith(SpringRunner.class)
public class CharacterBoTest {

	@Autowired
	private CharacterRepository characterRepository;
	
	@Autowired
	private SpellRepository spellRepository;
	
	@Test
	public void test01() {
		
		//Characters
		Optional<Character> characters = this.characterRepository.findByName(Character.CharacterNameEnum.JAINA.name());
		assertThat(characters).isNotEmpty();
		
		//Spells
		Page<Spell> spells = this.spellRepository.findByName(Spell.SpellNameEnum.FLASH_OF_FROST.name(), PageRequest.of(0, 5));
		assertThat(spells).isNotEmpty();
		
		spells.getContent().forEach(c -> System.out.println(" > Spell is " + c.toString()));
	}
}
