package br.com.apidoacao.entrypoint.impl;

import br.com.apidoacao.entrypoint.ConsultaEnderecoPorCepController;
import br.com.apidoacao.entrypoint.exception.ValidacaoControllerException;
import br.com.apidoacao.entrypoint.json.response.DataResponse;
import br.com.apidoacao.entrypoint.json.response.EnderecoResponse;
import br.com.apidoacao.entrypoint.mapper.EnderecoEntrypointMapper;
import br.com.apidoacao.usecase.ConsultaEnderecoPorCepUsecase;
import br.com.apidoacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ConsultaEnderecoPorCepControllerImpl implements ConsultaEnderecoPorCepController {

    @Autowired
    private ConsultaEnderecoPorCepUsecase usecase;

    @Autowired
    private EnderecoEntrypointMapper mapper;

    @Override
    public ResponseEntity<DataResponse<EnderecoResponse>> consultaEnderecoPorCep(String cep) {

        log.info("Iniciando a consulta de endereco por cep {}", cep);

        validarCep(cep);

        return ResponseEntity.ok(DataResponse
                .<EnderecoResponse>builder()
                .data(mapper
                        .toJson(usecase
                                .consultarPorCep(cep)))
                .build());
    }

    private void validarCep(String cep) {

        log.info("Iniciando a validação do cep {}", cep);

        try {
            if(cep.isBlank() || cep.length() != 8) {
                throw new ValidacaoControllerException(ConstanteUtils.ERRO_CAMPO_INVALIDO);
            }
        } catch (ValidacaoControllerException ex) {
            log.error("Erro na validação do cep {}", cep);

            throw ex;
        }
    }

}
