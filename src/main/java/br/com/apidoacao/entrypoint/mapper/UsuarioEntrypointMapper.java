package br.com.apidoacao.entrypoint.mapper;

import br.com.apidoacao.domain.Usuario;
import br.com.apidoacao.entrypoint.json.request.AutenticarUsuarioRequest;
import br.com.apidoacao.entrypoint.json.request.CadastroUsuarioRequest;
import br.com.apidoacao.entrypoint.json.response.UsuarioResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioEntrypointMapper {

    Usuario cadastroUsuarioRequestToDomain(CadastroUsuarioRequest request);

    Usuario autenticarUsuarioRequestToDomain(AutenticarUsuarioRequest request);

    UsuarioResponse toUsuarioResponseJson(Usuario domain);

}
