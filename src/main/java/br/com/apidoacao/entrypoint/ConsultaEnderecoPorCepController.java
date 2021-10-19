package br.com.apidoacao.entrypoint;

import br.com.apidoacao.entrypoint.json.response.DataResponse;
import br.com.apidoacao.entrypoint.json.response.EnderecoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ConsultaEnderecoPorCepController {

    @GetMapping("/v1/endereco/{cep}")
    ResponseEntity<DataResponse<EnderecoResponse>> consultaEnderecoPorCep(@PathVariable String cep);

}
