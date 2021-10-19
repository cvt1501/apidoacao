package br.com.apidoacao.entrypoint;

import br.com.apidoacao.entrypoint.json.response.DataResponse;
import br.com.apidoacao.entrypoint.json.response.PoloResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ConsultaPolosController {

    @GetMapping("/v1/polos")
    ResponseEntity<DataResponse<List<PoloResponse>>> consultarPolos();

}
