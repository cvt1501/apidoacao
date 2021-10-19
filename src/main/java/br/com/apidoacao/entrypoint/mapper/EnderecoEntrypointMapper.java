package br.com.apidoacao.entrypoint.mapper;

import br.com.apidoacao.domain.Endereco;
import br.com.apidoacao.entrypoint.json.response.EnderecoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoEntrypointMapper {

    EnderecoResponse toJson(Endereco domain);

}
