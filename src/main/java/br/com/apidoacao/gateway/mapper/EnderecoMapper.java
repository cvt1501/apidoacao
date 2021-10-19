package br.com.apidoacao.gateway.mapper;

import br.com.apidoacao.domain.Endereco;
import br.com.apidoacao.gateway.feign.json.response.EnderecoFeignResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco toDomain(EnderecoFeignResponse json);

}
