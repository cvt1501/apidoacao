package br.com.apidoacao.usecase;

import br.com.apidoacao.domain.Polo;
import br.com.apidoacao.gateway.PoloGateway;
import br.com.apidoacao.usecase.exception.DadosNaoEncontradoUsecaseException;
import br.com.apidoacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ConsultaPolosUsecase {

    @Autowired
    private PoloGateway gateway;

    public List<Polo> consultarPolos() {

        log.info("Iniciando a consulta de polos");

        try {
            final List<Polo> polos = gateway.consultarPolos();

            if(polos.isEmpty()) throw new DadosNaoEncontradoUsecaseException(ConstanteUtils.ERRO_DADOS_NAO_ENCONTRADO);

            return polos;
        } catch (DadosNaoEncontradoUsecaseException ex) {
            log.error("Dados de polo não encontrados, causa {}", ex.getMessage());

            throw ex;
        }
    }

}
