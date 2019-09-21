package com.instantor.dap.springbootbackend.character;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.instantor.dap.springbootbackend.character.dto.CharacterDto;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CharacterFacade {

    CharacterRepository characters;

    public void add(CharacterDto character) {
        characters.save(Character.ofDto(character));
    }

    public Optional<CharacterDto> findOne(Long id) {
        return characters.findById(id)
                .map(Character::asDto);
    }

    public Page<CharacterDto> findAll(Pageable pageable) {
        return characters.findAll(pageable)
                .map(Character::asDto);
    }

    public Page<CharacterDto> findByName(String name, Pageable pageable) {
        return characters.findByName(name, pageable)
                .map(Character::asDto);
    }
}
