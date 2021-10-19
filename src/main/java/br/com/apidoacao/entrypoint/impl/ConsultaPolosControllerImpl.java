package br.com.apidoacao.entrypoint.impl;

import br.com.apidoacao.entrypoint.ConsultaPolosController;
import br.com.apidoacao.entrypoint.json.response.DataResponse;
import br.com.apidoacao.entrypoint.json.response.PoloResponse;
import br.com.apidoacao.entrypoint.mapper.PoloEntrypointMapper;
import br.com.apidoacao.usecase.ConsultaPolosUsecase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class ConsultaPolosControllerImpl implements ConsultaPolosController {

    @Autowired
    private ConsultaPolosUsecase usecase;

    @Autowired
    private PoloEntrypointMapper mapper;

    @Override
    public ResponseEntity<DataResponse<List<PoloResponse>>> consultarPolos() {

        log.info("Iniciando consulta de polos");

        return ResponseEntity.ok(DataResponse
                .<List<PoloResponse>>builder()
                .data(usecase
                        .consultarPolos()
                        .stream()
                        .map(mapper::domainToResponse)
                        .collect(Collectors.toList()))
                .build());
    }

}
