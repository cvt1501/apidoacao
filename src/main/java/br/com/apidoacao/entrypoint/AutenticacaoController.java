package br.com.apidoacao.entrypoint;

import br.com.apidoacao.entrypoint.json.request.AutenticarUsuarioRequest;
import br.com.apidoacao.entrypoint.json.response.DataResponse;
import br.com.apidoacao.entrypoint.json.response.AutenticarUsuarioResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AutenticacaoController {

    @PostMapping("/signin")
    ResponseEntity<DataResponse<AutenticarUsuarioResponse>> autenticarUsuario(@RequestBody AutenticarUsuarioRequest request);

}
