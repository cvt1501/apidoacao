package br.com.apidoacao.entrypoint.impl;

import br.com.apidoacao.entrypoint.CadastroUsuarioController;
import br.com.apidoacao.entrypoint.exception.ValidacaoControllerException;
import br.com.apidoacao.entrypoint.json.request.CadastroUsuarioRequest;
import br.com.apidoacao.entrypoint.json.response.CadastroUsuarioResponse;
import br.com.apidoacao.entrypoint.json.response.DataResponse;
import br.com.apidoacao.entrypoint.mapper.UsuarioEntrypointMapper;
import br.com.apidoacao.usecase.CadastroUsuarioUsecase;
import br.com.apidoacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CadastroUsuarioControllerImpl implements CadastroUsuarioController {

    @Autowired
    private CadastroUsuarioUsecase usecase;

    @Autowired
    private UsuarioEntrypointMapper mapper;

    @Override
    public ResponseEntity<DataResponse<CadastroUsuarioResponse>> cadastrarUsuario(CadastroUsuarioRequest request) {

        log.info("Iniciando cadastro do usuario de email {}", request.getEmail());

        validarRequest(request);

        usecase.cadastrarUsuario(mapper.cadastroUsuarioRequestToDomain(request));

        return ResponseEntity.ok(DataResponse
                .<CadastroUsuarioResponse>builder()
                .data(CadastroUsuarioResponse
                        .builder()
                        .mensagem("Usuario Cadastrado com sucesso!")
                        .build())
                .build());
    }

    private void validarRequest(CadastroUsuarioRequest request) {

        log.info("Validando as propiedades do request");

        try {
            if(request.getEmail().isBlank() || request.getSenha().isBlank() || request.getDoador() == null) {
                throw new ValidacaoControllerException(ConstanteUtils.ERRO_CAMPO_INVALIDO);
            }
        } catch (ValidacaoControllerException ex) {
            log.error("Erro ao validar propiedades do request, causa {}", ex.getMessage());

            throw ex;
        }
    }

}
