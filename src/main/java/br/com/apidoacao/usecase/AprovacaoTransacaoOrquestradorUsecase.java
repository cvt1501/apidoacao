package br.com.apidoacao.usecase;

import br.com.apidoacao.domain.Transacao;
import br.com.apidoacao.service.message.MensagemTransacaoAprovada;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Transactional
@Service
public class AprovacaoTransacaoOrquestradorUsecase {

    @Autowired
    private AtualizacaoStatusTransacaoUsecase atualizacaoStatusTransacaoUsecase;

    @Autowired
    private AtualizacaoPontosDoadorUsecase atualizacaoPontosDoadorUsecase;

    public void atualizarInformacoes(MensagemTransacaoAprovada mensagemTransacaoAprovada) {

        log.info("Iniciando a atualização de transação e doador");

        final Transacao transacao = atualizacaoStatusTransacaoUsecase.atualizarStatusTransacao(mensagemTransacaoAprovada);
        final Integer idDoador = transacao.getDoador().getIdDoador();
        final Long pontos = transacao.getPontos();

        atualizacaoPontosDoadorUsecase.atualizarPontosDoador(idDoador, pontos);
    }

}
