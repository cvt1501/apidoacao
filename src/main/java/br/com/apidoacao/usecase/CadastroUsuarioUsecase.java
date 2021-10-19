package br.com.apidoacao.usecase;

import br.com.apidoacao.domain.Usuario;
import br.com.apidoacao.gateway.UsuarioGateway;
import br.com.apidoacao.usecase.exception.ErroDeCadastroUsecaseException;
import br.com.apidoacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CadastroUsuarioUsecase {

    @Autowired
    private UsuarioGateway gateway;

    public Usuario cadastrarUsuario(Usuario usuario) {

        log.info("Iniciando o cadastro do usuario de email {}", usuario.getEmail());

        try {
            final Usuario usuarioCadastrado = gateway.cadastrarUsuario(usuario);

            if(usuarioCadastrado == null) throw new ErroDeCadastroUsecaseException(String.format(ConstanteUtils.ERRO_CADASTRAR_DADO, "usuario"));

            return usuarioCadastrado;
        } catch (ErroDeCadastroUsecaseException ex) {
            log.error("Erro no cadastro do usuario de email {}, causa {}", usuario.getEmail(), ex.getMessage());

            throw ex;
        }
    }

}
