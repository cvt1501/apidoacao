package br.com.apidoacao.fixtures;

import lombok.Getter;

@Getter
public enum BaseFixtures {

    ALL("br.com.apidoacao.fixtures"),
    ENTITY("br.com.apidoacao.fixtures.entity"),
    DOMAIN("br.com.apidoacao.fixtures.domain"),
    JSON("br.com.apidoacao.fixtures.json");

    private final String pacote;

    BaseFixtures(final String pacote) {
        this.pacote = pacote;
    }
}
