package com.instantor.dap.springbootbackend.character.web

import com.instantor.dap.springbootbackend.ContractSpecBase
import com.instantor.dap.springbootbackend.character.CharacterFacade
import org.spockframework.spring.SpringBean
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

import static com.instantor.dap.springbootbackend.character.CharacterMother.Leia
import static com.instantor.dap.springbootbackend.character.CharacterMother.Luke

@WebMvcTest(controllers = CharacterController)
abstract class CharacterContractSpecBase extends ContractSpecBase {

    @SpringBean CharacterFacade characters = Stub()

    @Override
    void setup() {
        characters.findAll(_ as Pageable) >> { Pageable pageable -> new PageImpl<>([Luke, Leia], pageable, 2) }
        characters.findByName('Leia', _ as Pageable)  >> { name, pageable -> new PageImpl<>([Leia], pageable, 1) }
        characters.findOne(Luke.id) >> Optional.of(Luke)
    }
}
