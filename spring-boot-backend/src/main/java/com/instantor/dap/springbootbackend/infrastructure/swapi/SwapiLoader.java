package com.instantor.dap.springbootbackend.infrastructure.swapi;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.instantor.dap.springbootbackend.character.CharacterFacade;
import com.instantor.dap.springbootbackend.character.dto.CharacterDto;
import com.instantor.dap.springbootbackend.infrastructure.Reference;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
class SwapiLoader implements ApplicationRunner {

    SwapiClient swapi;
    CharacterFacade characters;

    @Override
    public void run(ApplicationArguments args) {
        Page<Person> people = swapi.getPeople(PageRequest.of(0, 10));
        while (!people.isEmpty()) {
            people.forEach(person -> {
                characters.add(CharacterDto.builder()
                        .id(person.getId())
                        .name(person.getName())
                        .gender(person.getGender())
                        .birthYear(person.getBirth())
                        .height(person.getHeight())
                        .weight(person.getMass())
                        .skinColor(person.getSkin())
                        .eyeColor(person.getEye())
                        .hairColor(person.getHair())
                        .homeWorld(homeRef(person))
                        .species(speciesRef(person))
                        .build());
            });
            if (people.hasNext()) {
                people = swapi.getPeople(people.nextPageable());
            } else {
                people = Page.empty();
            }
        }
    }

    private Reference homeRef(Person person) {
        return Optional.ofNullable(person.getHomeId())
                .flatMap(swapi::getPlanet)
                .map(planet -> Reference.builder()
                        .id(planet.getId())
                        .name(planet.getName())
                        .build())
                .orElse(null);
    }

    private List<Reference> speciesRef(Person person) {
        return Optional.ofNullable(person.getSpeciesIds())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(swapi::getSpecies)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(species -> Reference.builder()
                        .id(species.getId())
                        .name(species.getName())
                        .build())
                .collect(toList());
    }
}
