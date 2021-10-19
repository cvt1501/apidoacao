package br.com.apidoacao.entrypoint;

import br.com.apidoacao.entrypoint.json.request.DoacaoRequest;
import br.com.apidoacao.entrypoint.json.response.DataResponse;
import br.com.apidoacao.entrypoint.json.response.TransacaoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface CadastroDoacaoController {

    @PostMapping("/v1/doacao")
    ResponseEntity<DataResponse<TransacaoResponse>> cadastrarDoacao(@RequestHeader("Authorization") String token, @RequestBody DoacaoRequest request);

}
