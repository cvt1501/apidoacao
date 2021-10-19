package br.com.apidoacao.entrypoint.impl;

import br.com.apidoacao.entrypoint.ConsultaUsuarioController;
import br.com.apidoacao.entrypoint.json.response.DataResponse;
import br.com.apidoacao.entrypoint.json.response.UsuarioResponse;
import br.com.apidoacao.entrypoint.mapper.UsuarioEntrypointMapper;
import br.com.apidoacao.security.JwtService;
import br.com.apidoacao.usecase.ConsultaUsuarioUsecase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ConsultaUsuarioControllerImpl implements ConsultaUsuarioController {

    @Autowired
    private ConsultaUsuarioUsecase usecase;

    @Autowired
    private UsuarioEntrypointMapper mapper;

    @Autowired
    private JwtService service;

    @Override
    public ResponseEntity<DataResponse<UsuarioResponse>> consultarUsuario(String token) {

        log.info("Iniciando a consulta de usuario");

        final String emailUsuario = service.obterEmailUsuario(token);

        return ResponseEntity.ok(DataResponse
                .<UsuarioResponse>builder()
                .data(mapper
                        .toUsuarioResponseJson(usecase
                                .consultarUsuario(emailUsuario)))
                .build());
    }

}
