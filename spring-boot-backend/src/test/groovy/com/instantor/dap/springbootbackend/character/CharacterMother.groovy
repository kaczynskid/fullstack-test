package com.instantor.dap.springbootbackend.character

import com.instantor.dap.springbootbackend.character.dto.CharacterDto
import com.instantor.dap.springbootbackend.infrastructure.Reference

class CharacterMother {

    public static final Reference HumanRef = Reference.builder()
            .id(1)
            .name('Human')
            .build();

    public static final Reference TatooineRef = Reference.builder()
            .id(1)
            .name('Tatooine')
            .build();

    public static final Reference AlderaanRef = Reference.builder()
            .id(2)
            .name('Alderaan')
            .build();

    public static final CharacterDto Luke = CharacterDto.builder()
            .id(1)
            .name('Luke Skywalker')
            .gender('male')
            .birthYear('19BBY')
            .height('172')
            .weight('77')
            .skinColor('fair')
            .eyeColor('blue')
            .hairColor('blond')
            .homeWorld(TatooineRef)
            .species(Arrays.asList(HumanRef))
            .build();

    public static final CharacterDto Leia = CharacterDto.builder()
            .id(5)
            .name('Leia Organa')
            .gender('female')
            .birthYear('19BBY')
            .height('150')
            .weight('49')
            .skinColor('light')
            .eyeColor('brown')
            .hairColor('brown')
            .homeWorld(AlderaanRef)
            .species(Arrays.asList(HumanRef))
            .build();
}
