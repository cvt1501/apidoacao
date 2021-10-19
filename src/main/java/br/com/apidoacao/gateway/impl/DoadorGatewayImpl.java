package br.com.apidoacao.gateway.impl;

import br.com.apidoacao.domain.Doador;
import br.com.apidoacao.gateway.DoadorGateway;
import br.com.apidoacao.gateway.entity.DoadorEntity;
import br.com.apidoacao.gateway.exception.GatewayException;
import br.com.apidoacao.gateway.mapper.DoadorMapper;
import br.com.apidoacao.gateway.repository.DoadorRepository;
import br.com.apidoacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class DoadorGatewayImpl implements DoadorGateway {

    @Autowired
    private DoadorRepository repository;

    @Autowired
    private DoadorMapper mapper;

    @Override
    public Optional<Doador> consultarDoadorPorEmail(String email) {

        log.info("Iniciando a consulta do doador de email {}", email);

        try {
            final Optional<DoadorEntity> optionalDoadorEntity = repository.findByUserEmail(email);

            return optionalDoadorEntity
                    .map(mapper::toDomain)
                    .stream()
                    .findFirst();
        } catch (RuntimeException ex) {
            log.error("Erro ao realizar consulta do doador de email {}, causa {}", email, ex.getMessage());

            throw new GatewayException(String.format(ConstanteUtils.ERRO_CONSULTAR_DADO, "doador"));
        }
    }

    @Override
    public Optional<Doador> consultarDoadorPorId(Integer idDoador) {
        log.info("Iniciando a consulta do doador de id {}", idDoador);

        try {
            final Optional<DoadorEntity> optionalDoadorEntity = repository.findById(idDoador);

            return optionalDoadorEntity
                    .map(mapper::toDomain)
                    .stream()
                    .findFirst();
        } catch (RuntimeException ex) {
            log.error("Erro ao realizar consulta do doador de id {}, causa {}", idDoador, ex.getMessage());

            throw new GatewayException(String.format(ConstanteUtils.ERRO_CONSULTAR_DADO, "doador"));
        }
    }

    @Override
    public Doador atualizarDoador(Doador doador) {

        log.info("Iniciando a atualização do doador");

        try {
            final DoadorEntity doadorEntity = mapper.toEntity(doador);

            return mapper.toDomain(repository.save(doadorEntity));
        } catch (RuntimeException ex) {
            log.error("Erro ao realizar a atualização do doador de nome {}, causa {}", doador.getNome(), ex.getMessage());

            throw new GatewayException(String.format(ConstanteUtils.ERRO_ATUALIZAR_DADO, "doador"));
        }
    }

}
