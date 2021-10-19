package br.com.apidoacao.gateway.mapper;

import br.com.apidoacao.domain.Doador;
import br.com.apidoacao.gateway.entity.DoadorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoadorMapper {

    Doador toDomain(DoadorEntity entity);

    DoadorEntity toEntity(Doador domain);

}
