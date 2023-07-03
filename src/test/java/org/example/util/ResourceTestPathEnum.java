package org.example.util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ResourceTestPathEnum {

    DOMAIN_MODEL_PATH("domain/models/"),
    ENTITIES_PATH("infrastructure/repository/entity/"),
    REST_MODEL_PATH("infrastructure/rest/models/");

    private static final String BASE_PATH = "classpath:org/example/application/";
    private final String path;

    public String get() {
        return BASE_PATH + path;
    }
}
