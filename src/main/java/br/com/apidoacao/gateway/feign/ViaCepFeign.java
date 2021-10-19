package br.com.apidoacao.gateway.feign;

import br.com.apidoacao.gateway.feign.json.response.EnderecoFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${spring.feign.clients.cep.name}", url = "${spring.feign.clients.cep.base-uri}")
public interface ViaCepFeign {

    @GetMapping("/ws/{cep}/json")
    EnderecoFeignResponse findEnderecoByCep(final @PathVariable String cep);

}
