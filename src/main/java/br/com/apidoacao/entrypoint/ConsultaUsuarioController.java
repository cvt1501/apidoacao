package br.com.apidoacao.entrypoint;

import br.com.apidoacao.entrypoint.json.response.DataResponse;
import br.com.apidoacao.entrypoint.json.response.UsuarioResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

public interface ConsultaUsuarioController {

    @GetMapping("/v1/usuario")
    ResponseEntity<DataResponse<UsuarioResponse>> consultarUsuario(@RequestHeader("Authorization") String token);

}
