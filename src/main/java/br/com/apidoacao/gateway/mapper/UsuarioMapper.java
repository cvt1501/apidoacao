package br.com.apidoacao.gateway.mapper;

import br.com.apidoacao.domain.Usuario;
import br.com.apidoacao.gateway.entity.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toDomain(UsuarioEntity entity);

    UsuarioEntity toEntity(Usuario domain);

}
