package com.instantor.dap.springbootbackend;

import org.springframework.http.MediaType;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.MediaType.valueOf;

@NoArgsConstructor(access = PRIVATE)
public class StarWarsMediaType {

    public static final MediaType STAR_WARS_JSON_V1;

    public static final String STAR_WARS_JSON_V1_VALUE = "application/vnd.star-wars.v1+json;charset=UTF-8";

    static {
        STAR_WARS_JSON_V1 = valueOf(STAR_WARS_JSON_V1_VALUE);
    }
}
