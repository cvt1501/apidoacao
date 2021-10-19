package br.com.apidoacao.usecase;

import br.com.apidoacao.domain.Transacao;
import br.com.apidoacao.domain.enums.StatusTransacaoEnum;
import br.com.apidoacao.gateway.TransacaoGateway;
import br.com.apidoacao.service.TransacaoProducerService;
import br.com.apidoacao.usecase.exception.ErroDeCadastroUsecaseException;
import br.com.apidoacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CadastroTransacaoUsecase {

    @Autowired
    private TransacaoGateway gateway;

    @Autowired
    private TransacaoProducerService transacaoProducerService;

    public Transacao cadastrarTransacao(Transacao transacao) {

        log.info("Iniciando o cadastro da transacao");

        try {
            transacao.setPontos(ConstanteUtils.PONTOS_POR_LIVRO * transacao.getLivros().size());
            transacao.setStatusTransacao(StatusTransacaoEnum.PENDENTE);

            final Transacao transacaoSalva = gateway.cadastrarTransacao(transacao);

            if (transacaoSalva == null) throw new ErroDeCadastroUsecaseException(String.format(ConstanteUtils.ERRO_CADASTRAR_DADO, "transacão"));

            transacaoProducerService.send(transacaoSalva);

            return transacaoSalva;
        } catch (ErroDeCadastroUsecaseException ex) {
            log.error("Erro ao cadastrar transacao, causa {}", ex.getMessage());

            throw ex;
        }
    }

}
