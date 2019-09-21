package com.instantor.dap.springbootbackend.character;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

interface CharacterRepository extends Repository<Character, Long> {

    void save(Character character);

    Optional<Character> findById(Long id);

    Page<Character> findAll(Pageable pageable);

    Page<Character> findByName(String name, Pageable pageable);
}
