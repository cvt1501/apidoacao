package br.com.apidoacao.usecase;

import br.com.apidoacao.domain.Transacao;
import br.com.apidoacao.gateway.TransacaoGateway;
import br.com.apidoacao.service.message.MensagemTransacaoAprovada;
import br.com.apidoacao.usecase.exception.DadosNaoEncontradoUsecaseException;
import br.com.apidoacao.usecase.exception.ErroDeAtualizacaoUsecaseException;
import br.com.apidoacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AtualizacaoStatusTransacaoUsecase {

    @Autowired
    private TransacaoGateway gateway;

    public Transacao atualizarStatusTransacao(MensagemTransacaoAprovada mensagemTransacaoAprovada) {

        log.info("Iniciando a atualização do status da transacao de id {}", mensagemTransacaoAprovada.getIdTransacao());

        try {
            final Transacao transacao = gateway.consultarTransacaoPorId(mensagemTransacaoAprovada.getIdTransacao()).orElseThrow(() -> new DadosNaoEncontradoUsecaseException(ConstanteUtils.ERRO_DADOS_NAO_ENCONTRADO));

            transacao.setStatusTransacao(mensagemTransacaoAprovada.getStatusTransacao());

            final Transacao transacaoAtualizada = gateway.atualizarTransacao(transacao);

            if(transacaoAtualizada == null) throw new ErroDeAtualizacaoUsecaseException(String.format(ConstanteUtils.ERRO_ATUALIZAR_DADO, "transacao"));

            return transacaoAtualizada;
        } catch (DadosNaoEncontradoUsecaseException | ErroDeAtualizacaoUsecaseException ex) {
            log.error("Erro ao atualizar status da transacao de id {}, causa {}", mensagemTransacaoAprovada.getIdTransacao(), ex.getMessage());

            throw ex;
        }
    }
}
