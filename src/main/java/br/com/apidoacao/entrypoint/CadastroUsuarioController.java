package br.com.apidoacao.entrypoint;

import br.com.apidoacao.entrypoint.json.request.CadastroUsuarioRequest;
import br.com.apidoacao.entrypoint.json.response.DataResponse;
import br.com.apidoacao.entrypoint.json.response.CadastroUsuarioResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CadastroUsuarioController {

    @PostMapping("/signup")
    ResponseEntity<DataResponse<CadastroUsuarioResponse>> cadastrarUsuario(@RequestBody CadastroUsuarioRequest request);

}
