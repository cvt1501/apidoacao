package br.com.apidoacao.gateway;

import br.com.apidoacao.domain.Endereco;

public interface EnderecoViaCepGateway {

    Endereco consultarEnderecoPorCep(String cep);

}
