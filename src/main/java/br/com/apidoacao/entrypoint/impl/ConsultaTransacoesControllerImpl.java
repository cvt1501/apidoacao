package br.com.apidoacao.entrypoint.impl;

import br.com.apidoacao.entrypoint.ConsultaTransacoesController;
import br.com.apidoacao.entrypoint.json.response.DataResponse;
import br.com.apidoacao.entrypoint.json.response.TransacaoResponse;
import br.com.apidoacao.entrypoint.mapper.DoacaoEntrypointMapper;
import br.com.apidoacao.security.JwtService;
import br.com.apidoacao.usecase.ConsultaTransacoesUsecase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class ConsultaTransacoesControllerImpl implements ConsultaTransacoesController {

    @Autowired
    private ConsultaTransacoesUsecase usecase;

    @Autowired
    private JwtService service;

    @Autowired
    private DoacaoEntrypointMapper mapper;

    @Override
    public ResponseEntity<DataResponse<List<TransacaoResponse>>> consultaTransacoesPorEmail(String token) {

        log.info("Iniciando consulta de transacoes do usuario");

        final String emailUsuario = service.obterEmailUsuario(token);

        return ResponseEntity.ok(DataResponse
                .<List<TransacaoResponse>>builder()
                .data(usecase.consultaPorEmail(emailUsuario)
                        .stream()
                        .map(mapper::domainToResponse)
                        .collect(Collectors.toList()))
                .build());
    }

}
