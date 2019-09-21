package com.instantor.dap.springbootbackend.character.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.instantor.dap.springbootbackend.character.CharacterFacade;
import com.instantor.dap.springbootbackend.character.dto.CharacterDto;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import static com.instantor.dap.springbootbackend.StarWarsMediaType.STAR_WARS_JSON_V1_VALUE;
import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
class CharacterController {

    CharacterFacade characters;

    @GetMapping(produces = STAR_WARS_JSON_V1_VALUE)
    Page<CharacterDto> find(@RequestParam(name = "search", required = false) String name, Pageable pageable) {
        if (StringUtils.hasText(name)) {
            return characters.findByName(name, pageable);
        } else {
            return characters.findAll(pageable);
        }
    }

    @GetMapping(path = "/{id}", produces = STAR_WARS_JSON_V1_VALUE)
    ResponseEntity<?> get(@PathVariable("id") Long id) {
        return characters.findOne(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
