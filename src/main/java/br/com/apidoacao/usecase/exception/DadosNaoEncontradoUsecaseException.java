package br.com.apidoacao.usecase.exception;

import br.com.apidoacao.gateway.exception.GatewayException;

public class DadosNaoEncontradoUsecaseException extends GatewayException {

    public DadosNaoEncontradoUsecaseException(String message) {
        super(message);
    }

}
