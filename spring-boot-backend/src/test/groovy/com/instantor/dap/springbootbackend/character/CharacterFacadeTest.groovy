package com.instantor.dap.springbootbackend.character

import com.instantor.dap.springbootbackend.character.dto.CharacterDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import spock.lang.Specification

import static com.instantor.dap.springbootbackend.character.CharacterMother.Leia
import static com.instantor.dap.springbootbackend.character.CharacterMother.Luke

class CharacterFacadeTest extends Specification {

    CharacterFacade characters = new CharacterModuleConfig().characterFacade()

    def "should find character by id"() {
        given: "we have Luke"
            characters.add(Luke)

        when: "we ask for Luke by id"
            Optional<CharacterDto> found = characters.findOne(Luke.id)

        then: "system returns Luke"
            found.present
            found.get() == Luke
    }

    def "should list characters"() {
        given: "we have Luke & Leia"
            characters.add(Luke)
            characters.add(Leia)

        when: "we ask for ten characters"
            Page<CharacterDto> found = characters.findAll(PageRequest.of(0, 10))

        then: "system returns Luke & Leia"
            found.contains(Luke)
            found.contains(Leia)
    }

    def "should list first page of characters"() {
        given: "we have Luke & Leia"
            characters.add(Luke)
            characters.add(Leia)

        when: "we ask for ten characters"
            Page<CharacterDto> found = characters.findAll(PageRequest.of(0, 1))

        then: "system returns Luke & Leia"
            found.contains(Luke)
            !found.contains(Leia)
    }

    def "should list second page of characters"() {
        given: "we have Luke & Leia"
            characters.add(Luke)
            characters.add(Leia)

        when: "we ask for ten characters"
            Page<CharacterDto> found = characters.findAll(PageRequest.of(1, 1))

        then: "system returns Luke & Leia"
            !found.contains(Luke)
            found.contains(Leia)
    }

    def "should find character by name"() {
        given: "we have Luke & Leia"
            characters.add(Luke)
            characters.add(Leia)

        when: "we look for Leia"
            Page<CharacterDto> found = characters.findByName(Leia.name, PageRequest.of(0, 10))

        then: "system returns Leia"
            !found.contains(Luke)
            found.contains(Leia)
    }

    def "should find character by single letter"() {
        given: "we have Luke & Leia"
            characters.add(Luke)
            characters.add(Leia)

        when: "we look for L"
            Page<CharacterDto> found = characters.findByName('L', PageRequest.of(0, 10))

        then: "system returns Luke & Leia"
            found.contains(Luke)
            found.contains(Leia)
    }

    def "should return empty when search string does not mtach"() {
        given: "we have Luke & Leia"
            characters.add(Luke)
            characters.add(Leia)

        when: "we look for L"
            Page<CharacterDto> found = characters.findByName('x', PageRequest.of(0, 10))

        then: "system returns empty result"
            found.empty
    }
}
