package br.com.apidoacao.entrypoint;

import br.com.apidoacao.entrypoint.json.response.DataResponse;
import br.com.apidoacao.entrypoint.json.response.TransacaoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface ConsultaTransacoesController {

    @GetMapping("/v1/transacoes")
    ResponseEntity<DataResponse<List<TransacaoResponse>>> consultaTransacoesPorEmail(@RequestHeader("Authorization") String token);

}
