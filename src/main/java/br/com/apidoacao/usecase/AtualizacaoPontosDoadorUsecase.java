package br.com.apidoacao.usecase;

import br.com.apidoacao.domain.Doador;
import br.com.apidoacao.gateway.DoadorGateway;
import br.com.apidoacao.usecase.exception.DadosNaoEncontradoUsecaseException;
import br.com.apidoacao.usecase.exception.ErroDeAtualizacaoUsecaseException;
import br.com.apidoacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AtualizacaoPontosDoadorUsecase {

    @Autowired
    private DoadorGateway gateway;

    public Doador atualizarPontosDoador(Integer idDoador, Long pontos) {

        log.info("Iniciando a atualização do pontos do doador de id {}", idDoador);

        try {
            Doador doador = gateway.consultarDoadorPorId(idDoador).orElseThrow(() -> new DadosNaoEncontradoUsecaseException(String.format(ConstanteUtils.ERRO_DADOS_NAO_ENCONTRADO, "doador")));

            final Long pontosSomados = doador.getPontos() + pontos;

            doador.setPontos(pontosSomados);

            final Doador doadorAtualizado = gateway.atualizarDoador(doador);

            if(doadorAtualizado == null) throw new ErroDeAtualizacaoUsecaseException(String.format(ConstanteUtils.ERRO_ATUALIZAR_DADO, "doador"));

            return doadorAtualizado;
        } catch (DadosNaoEncontradoUsecaseException | ErroDeAtualizacaoUsecaseException ex) {
            log.error("Erro ao atualizar os pontos do doador de id {}", idDoador);

            throw ex;
        }
    }

}
