package br.com.apidoacao.gateway.mapper;

import br.com.apidoacao.domain.Polo;
import br.com.apidoacao.gateway.entity.PoloEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PoloMapper {

    Polo toDomain(PoloEntity entity);

}
