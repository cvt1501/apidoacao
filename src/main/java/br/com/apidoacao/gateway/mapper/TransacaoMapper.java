package br.com.apidoacao.gateway.mapper;

import br.com.apidoacao.domain.Transacao;
import br.com.apidoacao.gateway.entity.TransacaoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransacaoMapper {

    Transacao toDomain(TransacaoEntity entity);
    
    TransacaoEntity toEntity(Transacao domain);

}
