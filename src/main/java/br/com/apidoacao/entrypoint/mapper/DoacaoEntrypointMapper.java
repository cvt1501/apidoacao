package br.com.apidoacao.entrypoint.mapper;

import br.com.apidoacao.domain.Transacao;
import br.com.apidoacao.entrypoint.json.request.DoacaoRequest;
import br.com.apidoacao.entrypoint.json.response.TransacaoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DoacaoEntrypointMapper {

    @Mapping(target = "statusTransacao", expression = "java(domain.getStatusTransacao().toString().toLowerCase())")
    TransacaoResponse domainToResponse(Transacao domain);

    @Mapping(source = "request.codigoPolo", target = "polo.codigoPolo")
    Transacao requestToDomain(DoacaoRequest request);

}
