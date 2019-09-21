package com.instantor.dap.springbootbackend.character;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import com.instantor.dap.springbootbackend.character.dto.CharacterDto;
import com.instantor.dap.springbootbackend.infrastructure.Identifiable;
import com.instantor.dap.springbootbackend.infrastructure.Reference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor(access = PACKAGE)
@AllArgsConstructor(access = PACKAGE)
@RedisHash("characters")
class Character implements Identifiable {

    @Id Long id;
    @Indexed String name;
    String gender;
    String birthYear;
    String height;
    String weight;
    String skinColor;
    String eyeColor;
    String hairColor;
    Reference homeWorld;
    List<Reference> species;

    static Character ofDto(CharacterDto dto) {
        return new Character(
                dto.getId(),
                dto.getName(),
                dto.getGender(),
                dto.getBirthYear(),
                dto.getHeight(),
                dto.getWeight(),
                dto.getSkinColor(),
                dto.getEyeColor(),
                dto.getHairColor(),
                dto.getHomeWorld(),
                dto.getSpecies()
        );
    }

    CharacterDto asDto() {
        return CharacterDto.builder()
                .id(id)
                .name(name)
                .gender(gender)
                .birthYear(birthYear)
                .height(height)
                .weight(weight)
                .skinColor(skinColor)
                .eyeColor(eyeColor)
                .hairColor(hairColor)
                .homeWorld(homeWorld)
                .species(species)
                .build();
    }
}
