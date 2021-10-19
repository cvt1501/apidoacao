package br.com.apidoacao.usecase;

import br.com.apidoacao.domain.Endereco;
import br.com.apidoacao.gateway.EnderecoViaCepGateway;
import br.com.apidoacao.usecase.exception.DadosNaoEncontradoUsecaseException;
import br.com.apidoacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsultaEnderecoPorCepUsecase {

    @Autowired
    private EnderecoViaCepGateway gateway;

    public Endereco consultarPorCep(String cep) {

        log.info("Iniciando a busca por cep {}", cep);

        try {
            final Endereco endereco = gateway.consultarEnderecoPorCep(cep);

            if(endereco == null) throw new DadosNaoEncontradoUsecaseException(ConstanteUtils.ERRO_DADOS_NAO_ENCONTRADO);

            return endereco;
        } catch (DadosNaoEncontradoUsecaseException ex) {
            log.error("Erro ao realizar busca por cep {}, causa {}", cep, ex.getMessage());

            throw ex;
        }
    }

}
