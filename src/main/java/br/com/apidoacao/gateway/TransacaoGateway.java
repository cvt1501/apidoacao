package br.com.apidoacao.gateway;

import br.com.apidoacao.domain.Transacao;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface TransacaoGateway {

    List<Transacao> consultarTransacoesPorEmail(String email);

    Optional<Transacao> consultarTransacaoPorId(Integer idTransacao);

    @Transactional
    Transacao cadastrarTransacao(Transacao transacao);

    @Transactional
    Transacao atualizarTransacao(Transacao transacao);

}
