package br.com.apidoacao.gateway;

import br.com.apidoacao.domain.Doador;

import java.util.Optional;

public interface DoadorGateway {

    Optional<Doador> consultarDoadorPorEmail(String email);

    Optional<Doador> consultarDoadorPorId(Integer idDoador);

    Doador atualizarDoador(Doador doador);

}
