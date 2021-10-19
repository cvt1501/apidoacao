package br.com.apidoacao.entrypoint.impl;

import br.com.apidoacao.domain.Transacao;
import br.com.apidoacao.entrypoint.CadastroDoacaoController;
import br.com.apidoacao.entrypoint.exception.ValidacaoControllerException;
import br.com.apidoacao.entrypoint.json.request.DoacaoRequest;
import br.com.apidoacao.entrypoint.json.response.DataResponse;
import br.com.apidoacao.entrypoint.json.response.TransacaoResponse;
import br.com.apidoacao.entrypoint.mapper.DoacaoEntrypointMapper;
import br.com.apidoacao.security.JwtService;
import br.com.apidoacao.usecase.CadastroDoacaoOrquestradorUsecase;
import br.com.apidoacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CadastroDoacaoControllerImpl implements CadastroDoacaoController {

    @Autowired
    private CadastroDoacaoOrquestradorUsecase usecase;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private DoacaoEntrypointMapper mapper;

    @Override
    public ResponseEntity<DataResponse<TransacaoResponse>> cadastrarDoacao(String token, DoacaoRequest request) {

        log.info("Iniciando o cadastro da doação");

        validarRequest(request);

        final String emailUsuario = jwtService.obterEmailUsuario(token);
        final Transacao transacao = mapper.requestToDomain(request);

        return ResponseEntity.ok(DataResponse
                .<TransacaoResponse>builder()
                .data(mapper
                        .domainToResponse(usecase
                                .cadastrarDoacao(emailUsuario, transacao)))
                .build());
    }

    private void validarRequest(DoacaoRequest request) {

        log.info("Validando as propiedades do request");

        try {
            if(request.getCodigoPolo() == 0 || request.getLivros().isEmpty()) {
                throw new ValidacaoControllerException(ConstanteUtils.ERRO_CAMPO_INVALIDO);
            }
        } catch (ValidacaoControllerException ex) {
            log.error("Erro ao validar propiedades do request, causa {}", ex.getMessage());

            throw ex;
        }
    }

}
