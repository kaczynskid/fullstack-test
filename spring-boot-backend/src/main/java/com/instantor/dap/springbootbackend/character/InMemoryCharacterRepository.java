package com.instantor.dap.springbootbackend.character;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.instantor.dap.springbootbackend.infrastructure.InMemoryRepository;

class InMemoryCharacterRepository extends InMemoryRepository<Character> implements CharacterRepository {

    @Override
    public void save(Character character) {
        super.save(character);
    }

    @Override
    public Optional<Character> findById(Long id) {
        return find(c -> c.getId().equals(id), PageRequest.of(0, 1))
                .getContent().stream().findAny();
    }

    @Override
    public Page<Character> findAll(Pageable pageable) {
        return find(this::all, pageable);
    }

    @Override
    public Page<Character> findByName(String name, Pageable pageable) {
        return find(c -> c.getName().contains(name), pageable);
    }
}
