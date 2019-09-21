package com.instantor.dap.springbootbackend.character

import com.instantor.dap.springbootbackend.IntegrationSpecBase
import com.instantor.dap.springbootbackend.character.dto.CharacterDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

import static com.instantor.dap.springbootbackend.character.CharacterMother.Leia
import static com.instantor.dap.springbootbackend.character.CharacterMother.Luke

class CharacterIntegrationTest extends IntegrationSpecBase {

    @Autowired CharacterFacade characters

    def "should add and return characters"() {
        given: "we have Luke & Leia"
            characters.add(Luke)
            characters.add(Leia)

        when: "we ask for ten characters"
            Page<CharacterDto> page = characters.findAll(PageRequest.of(0, 10))

        then: "system returns Luke & Leia"
            page.contains(Luke)
            page.contains(Leia)

        when: "we look for Leia by name"
            page = characters.findByName(Leia.name, PageRequest.of(0, 10))

        then: "system returns Leia"
            !page.contains(Luke)
            page.contains(Leia)

        when: "we ask for Luke by id"
            Optional<CharacterDto> found = characters.findOne(Luke.id)

        then: "system returns Luke"
            found.present
            found.get() == Luke
    }
}
