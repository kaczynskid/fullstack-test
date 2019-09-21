package com.instantor.dap.springbootbackend.infrastructure.swapi;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Pageable;

import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import static lombok.AccessLevel.PRIVATE;

@Data
@Slf4j
@ConfigurationProperties(prefix = "swapi")
@FieldDefaults(level = PRIVATE)
class SwapiProperties {

    /**
     * Star Wars API base URL.
     */
    String baseUrl;

    String peopleUrl(Pageable pageable) {
        return baseUrl + "/people/?page=" + (pageable.getPageNumber() + 1);
    }

    String planetUrl(Long id) {
        return baseUrl + "/planets/" + id + "/";
    }

    String speciesUrl(Long id) {
        return baseUrl + "/species/" + id + "/";
    }

    Long personId(String url) {
        return idOf("people", url);
    }

    Long planetId(String url) {
        return idOf("planets", url);
    }

    Long speciesId(String url) {
        return idOf("species", url);
    }

    private Long idOf(String type, String url) {
        try {
            return Long.parseLong(url.replace(baseUrl, "")
                    .replace(type, "")
                    .replaceAll("/", ""));
        } catch (NumberFormatException e) {
            log.info("Failed to extract ID", e);
            return null;
        }
    }
}
