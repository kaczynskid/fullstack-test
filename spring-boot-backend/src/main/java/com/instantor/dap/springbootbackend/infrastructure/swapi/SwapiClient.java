package com.instantor.dap.springbootbackend.infrastructure.swapi;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpMethod.GET;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
class SwapiClient {

    private static final HttpEntity EMPTY_HTTP_ENTITY;

    static {
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "curl/7.58.0"); // otherwise 403
        EMPTY_HTTP_ENTITY = new HttpEntity(headers);
    }


    SwapiProperties properties;
    RestTemplate rest;

    Page<Person> getPeople(Pageable pageable) {
        return getPage(pageable, Person.SLICE_TYPE)
                .map(person -> person.sanitizeReferences(properties));
    }

    Optional<Planet> getPlanet(Long id) {
        return findById(properties.planetUrl(id), Planet.class)
                .map(planet -> planet.sanitizeReferences(properties));
    }

    Optional<Species> getSpecies(Long id) {
        return findById(properties.speciesUrl(id), Species.class)
                .map(species -> species.sanitizeReferences(properties));
    }

    private <T> Page<T> getPage(Pageable pageable, ParameterizedTypeReference<Slice<T>> responseType) {
        String url = properties.peopleUrl(pageable);
        try {
            Slice<T> slice = rest.exchange(url, GET, EMPTY_HTTP_ENTITY, responseType).getBody();
            return new PageImpl<>(slice == null ? Collections.emptyList() : slice.getResults(),
                    pageable, slice == null ? 0 : slice.count);
        } catch (RestClientException e) {
            log.info("Error loading SWAPI data from " + url, e);
            return Page.empty();
        }
    }

    private <T> Optional<T> findById(String url, Class<T> responseType) {
        try {
            return Optional.ofNullable(rest.exchange(url, GET, EMPTY_HTTP_ENTITY, responseType).getBody());
        } catch (RestClientException e) {
            log.info("Error loading SWAPI data from " + url, e);
            return Optional.empty();
        }
    }
}

@Data
class Slice<T> {

    Long count;
    List<T> results;
}

@Data
class Person {

    static final ParameterizedTypeReference<Slice<Person>> SLICE_TYPE = new ParameterizedTypeReference<Slice<Person>>() {};

    String url;
    String name;
    String height;
    String mass;
    @JsonProperty("hair_color") String hair;
    @JsonProperty("skin_color") String skin;
    @JsonProperty("eye_color") String eye;
    @JsonProperty("birth_year") String birth;
    String gender;
    @JsonProperty("homeworld") String home;
    List<String> species;

    Long id;
    Long homeId;
    List<Long> speciesIds;

    Person sanitizeReferences(SwapiProperties properties) {
        id = url == null ? null : properties.personId(url);
        homeId = home == null ? null : properties.planetId(home);
        speciesIds = species == null ? null : species.stream().map(properties::speciesId).collect(toList());
        return this;
    }
}

@Data
class Planet {

    String url;
    String name;

    Long id;

    Planet sanitizeReferences(SwapiProperties properties) {
        id = url == null ? null : properties.planetId(url);
        return this;
    }
}

@Data
class Species {

    String url;
    String name;

    Long id;

    Species sanitizeReferences(SwapiProperties properties) {
        id = url == null ? null : properties.speciesId(url);
        return this;
    }
}
