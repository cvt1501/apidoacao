package br.com.apidoacao.entrypoint.mapper;

import br.com.apidoacao.domain.Polo;
import br.com.apidoacao.entrypoint.json.response.PoloResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PoloEntrypointMapper {

    PoloResponse domainToResponse(Polo polo);

}
