package com.valhalla.challenge_literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibrosDto(
        @JsonAlias("title") String nombreLibro,
        @JsonAlias("authors")List<AutoresDto> autores,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Double cantidadDescargas
        ) {
}
