package com.instantor.dap.springbootbackend.character;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CharacterModuleConfig {

    CharacterFacade characterFacade() {
        return characterFacade(new InMemoryCharacterRepository());
    }

    @Bean
    CharacterFacade characterFacade(CharacterRepository repository) {
        return new CharacterFacade(repository);
    }
}
