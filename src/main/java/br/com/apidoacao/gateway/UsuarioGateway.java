package br.com.apidoacao.gateway;

import br.com.apidoacao.domain.Usuario;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UsuarioGateway {

    Optional<Usuario> consultarUsuarioPorEmail(String email);

    @Transactional
    Usuario cadastrarUsuario(Usuario usuario);

}
