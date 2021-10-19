package br.com.apidoacao.usecase;

import br.com.apidoacao.domain.Polo;
import br.com.apidoacao.gateway.PoloGateway;
import br.com.apidoacao.usecase.exception.DadosNaoEncontradoUsecaseException;
import br.com.apidoacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsultaPoloPorCodigoUsecase {

    @Autowired
    private PoloGateway gateway;

    public Polo consultaPorCodigo(Long codigoPolo) {

        log.info("Iniciando a consulta do polo de codigo {}", codigoPolo);

        try {
            return gateway
                    .consultarPoloPorCodigo(codigoPolo)
                    .orElseThrow(() -> new DadosNaoEncontradoUsecaseException(ConstanteUtils.ERRO_DADOS_NAO_ENCONTRADO));
        } catch (DadosNaoEncontradoUsecaseException ex) {
            log.error("Erro ao consultar polo de codigo {}. causa {}", codigoPolo, ex.getMessage());

            throw ex;
        }

    }

}
