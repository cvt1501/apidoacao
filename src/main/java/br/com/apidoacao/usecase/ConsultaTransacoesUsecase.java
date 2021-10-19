package br.com.apidoacao.usecase;

import br.com.apidoacao.domain.Transacao;
import br.com.apidoacao.gateway.TransacaoGateway;
import br.com.apidoacao.usecase.exception.DadosNaoEncontradoUsecaseException;
import br.com.apidoacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ConsultaTransacoesUsecase {

    @Autowired
    private TransacaoGateway gateway;

    public List<Transacao> consultaPorEmail(String email) {

        log.info("Iniciando consulta de transacoes do usuario de email {}", email);

        try {
            final List<Transacao> transacoes = gateway.consultarTransacoesPorEmail(email);

            if (transacoes.isEmpty()) throw new DadosNaoEncontradoUsecaseException(ConstanteUtils.ERRO_DADOS_NAO_ENCONTRADO);

            return transacoes;
        } catch (DadosNaoEncontradoUsecaseException ex) {
            log.error("Não foi possivel encontrar nenhuma transação do usario de email {}, causa {}", email, ex.getMessage());

            throw ex;
        }
    }
}
