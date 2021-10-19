package br.com.apidoacao.gateway.impl;

import br.com.apidoacao.domain.Polo;
import br.com.apidoacao.gateway.PoloGateway;
import br.com.apidoacao.gateway.entity.PoloEntity;
import br.com.apidoacao.gateway.exception.GatewayException;
import br.com.apidoacao.gateway.mapper.PoloMapper;
import br.com.apidoacao.gateway.repository.PoloRepository;
import br.com.apidoacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class PoloGatewayImpl implements PoloGateway {

    @Autowired
    private PoloRepository repository;

    @Autowired
    private PoloMapper mapper;

    @Override
    public List<Polo> consultarPolos() {
        log.info("Iniciando a consulta do polos");

        try {
            final List<PoloEntity> polos = repository.findAll();

            return polos
                    .stream()
                    .map(mapper::toDomain)
                    .collect(Collectors.toList());
        } catch (RuntimeException ex) {
            log.error("Erro ao consultar dados de polos, causa {}", ex.getMessage());

            throw new GatewayException(String.format(ConstanteUtils.ERRO_CONSULTAR_DADO, "polo"));
        }
    }

    @Override
    public Optional<Polo> consultarPoloPorCodigo(Long codigoPolo) {
        log.info("Iniciando a consulta do polo de codigo {}", codigoPolo);

        try {
            final Optional<PoloEntity> optionalPoloEntity = repository.findByPoloCodigo(codigoPolo);

            return optionalPoloEntity
                    .map(mapper::toDomain)
                    .stream()
                    .findAny();
        } catch (RuntimeException ex) {
            log.error("Erro ao realizar consulta de polo de codigo {}, causa {}", codigoPolo, ex.getMessage());

            throw new GatewayException(String.format(ConstanteUtils.ERRO_CONSULTAR_DADO, "polo"));
        }
    }

}
