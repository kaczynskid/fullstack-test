package com.instantor.dap.springbootbackend.character.dto;

import java.util.List;

import com.instantor.dap.springbootbackend.infrastructure.Reference;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Builder
@Getter
@FieldDefaults(level = PRIVATE, makeFinal = true)
@EqualsAndHashCode
public class CharacterDto {

    Long id;
    String name;
    String gender;
    String birthYear;
    String height;
    String weight;
    String skinColor;
    String eyeColor;
    String hairColor;
    Reference homeWorld;
    List<Reference> species;
}
