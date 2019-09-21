package com.instantor.dap.springbootbackend.infrastructure;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@FieldDefaults(level = PRIVATE, makeFinal = true)
@EqualsAndHashCode
public class Reference {

    Long id;

    String name;
}
