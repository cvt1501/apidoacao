package br.com.apidoacao.usecase;

import br.com.apidoacao.domain.Doador;
import br.com.apidoacao.gateway.DoadorGateway;
import br.com.apidoacao.usecase.exception.DadosNaoEncontradoUsecaseException;
import br.com.apidoacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsultaDoadorUsecase {

    @Autowired
    private DoadorGateway doadorGateway;

    public Doador consultarPorEmail(String email) {

        log.info("Iniciando a consulta do doador de email {}", email);

        try {
            return doadorGateway
                    .consultarDoadorPorEmail(email)
                    .orElseThrow(() -> new DadosNaoEncontradoUsecaseException(ConstanteUtils.ERRO_DADOS_NAO_ENCONTRADO));
        } catch (DadosNaoEncontradoUsecaseException ex) {
            log.error("Erro ao consultar doador de email {}. causa {}", email, ex.getMessage());

            throw ex;
        }
    }

}
