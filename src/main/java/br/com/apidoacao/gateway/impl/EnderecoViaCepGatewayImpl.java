package br.com.apidoacao.gateway.impl;

import br.com.apidoacao.domain.Endereco;
import br.com.apidoacao.gateway.EnderecoViaCepGateway;
import br.com.apidoacao.gateway.exception.GatewayException;
import br.com.apidoacao.gateway.feign.ViaCepFeign;
import br.com.apidoacao.gateway.feign.json.response.EnderecoFeignResponse;
import br.com.apidoacao.gateway.mapper.EnderecoMapper;
import br.com.apidoacao.utils.ConstanteUtils;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EnderecoViaCepGatewayImpl implements EnderecoViaCepGateway {

    @Value("${spring.feign.clients.cep.name}")
    private String clienteFeignNome;

    @Autowired
    private ViaCepFeign feign;

    @Autowired
    private EnderecoMapper mapper;

    @Override
    public Endereco consultarEnderecoPorCep(String cep) {

        log.info("Integrando com API {} e buscando pelo cep {}", clienteFeignNome, cep);

        try {
            final EnderecoFeignResponse endereco = feign.findEnderecoByCep(cep);

            return mapper.toDomain(endereco);
        } catch (FeignException ex) {
            log.error("Erro ao realizar integração com api {}, causa {}", clienteFeignNome, ex.getMessage());

            throw new GatewayException(String.format(ConstanteUtils.ERRO_CONSULTAR_DADO, "endereco"));
        }
    }

}
