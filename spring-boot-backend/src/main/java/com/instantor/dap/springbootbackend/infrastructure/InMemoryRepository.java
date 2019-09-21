package com.instantor.dap.springbootbackend.infrastructure;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import lombok.experimental.FieldDefaults;

import static java.util.Comparator.comparing;
import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PROTECTED;

@FieldDefaults(level = PROTECTED, makeFinal = true)
public abstract class InMemoryRepository<T extends Identifiable> {

    AtomicLong seq = new AtomicLong();
    ConcurrentMap<Long, T> db = new ConcurrentHashMap<>();

    protected void save(T identifiable) {
        long key = keyFor(identifiable);
        identifiable.setId(key);
        db.put(key, identifiable);
    }

    private Long keyFor(T identifiable) {
        return ofNullable(identifiable.getId())
                .map(id -> {
                    seq.updateAndGet(prev -> Math.max(id, prev));
                    return id;
                })
                .orElseGet(seq::incrementAndGet);
    }

    protected Page<T> find(Predicate<T> filter, Pageable pageable) {
        List<T> content = db.values().stream()
                .filter(filter)
                .sorted(comparing(Identifiable::getId))
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(content, pageable, db.size());
    }

    protected boolean all(T identifiable) {
        return true;
    }
}
