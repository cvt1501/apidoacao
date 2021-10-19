package br.com.apidoacao.gateway.impl;

import br.com.apidoacao.domain.Transacao;
import br.com.apidoacao.gateway.TransacaoGateway;
import br.com.apidoacao.gateway.entity.TransacaoEntity;
import br.com.apidoacao.gateway.exception.GatewayException;
import br.com.apidoacao.gateway.mapper.TransacaoMapper;
import br.com.apidoacao.gateway.repository.TransacaoRepository;
import br.com.apidoacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TransacaoGatewayImpl implements TransacaoGateway {

    @Autowired
    private TransacaoRepository repository;

    @Autowired
    private TransacaoMapper mapper;

    @Override
    public List<Transacao> consultarTransacoesPorEmail(String email) {

        log.info("Iniciando consulta de transacoes do usuario de email {}", email);

        try {
            final List<TransacaoEntity> transacoesEntitys = repository.findByUserEmail(email);

            return transacoesEntitys
                    .stream()
                    .map(mapper::toDomain)
                    .collect(Collectors.toList());
        } catch (RuntimeException ex) {
            log.error("Não foi possivel encontrar nenhuma transação do usario de email {}, causa {}", email, ex.getMessage());

            throw new GatewayException(String.format(ConstanteUtils.ERRO_CONSULTAR_DADO, "transacoes"));
        }
    }

    @Override
    public Optional<Transacao> consultarTransacaoPorId(Integer idTransacao) {

        log.info("Iniciando a consulta da transação de id {}", idTransacao);

        try {
            final Optional<TransacaoEntity> optionalTransacaoEntity = repository.findById(idTransacao);

            return optionalTransacaoEntity
                    .map(mapper::toDomain)
                    .stream()
                    .findFirst();
        } catch (RuntimeException ex) {
            log.error("Erro ao realizar consulta de transacão de id {}, causa {}", idTransacao, ex.getMessage());

            throw new GatewayException(String.format(ConstanteUtils.ERRO_CONSULTAR_DADO, "transacoes"));
        }
    }

    @Override
    public Transacao cadastrarTransacao(Transacao transacao) {

        log.info("Iniciando o cadastro de uma transação");

        try {
            final TransacaoEntity transacaoEntity = mapper.toEntity(transacao);

            return mapper.toDomain(repository.save(transacaoEntity));
        } catch (RuntimeException ex) {
            log.error("Erro ao cadastrar transação, causa {}", ex.getMessage());

            throw new GatewayException(String.format(ConstanteUtils.ERRO_CADASTRAR_DADO, "transação"));
        }

    }

    @Override
    public Transacao atualizarTransacao(Transacao transacao) {

        log.info("Iniciando a atualização da transação de id {}", transacao.getIdTransacao());

        try {
            final TransacaoEntity transacaoEntity = mapper.toEntity(transacao);

            return mapper.toDomain(repository.save(transacaoEntity));
        } catch (RuntimeException ex) {
            log.error("Erro ao atualizar transação de id {}, causa {}", transacao.getIdTransacao(), ex.getMessage());

            throw ex;
        }
    }

}
