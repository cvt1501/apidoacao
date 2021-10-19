package br.com.apidoacao.gateway;

import br.com.apidoacao.domain.Polo;

import java.util.List;
import java.util.Optional;

public interface PoloGateway {

    List<Polo> consultarPolos();

    Optional<Polo> consultarPoloPorCodigo(Long codigoPolo);

}
