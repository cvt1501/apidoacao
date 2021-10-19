package br.com.apidoacao.usecase;

import br.com.apidoacao.domain.Usuario;
import br.com.apidoacao.gateway.UsuarioGateway;
import br.com.apidoacao.usecase.exception.UsuarioNaoEncontradoUsecaseException;
import br.com.apidoacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsultaUsuarioUsecase {

    @Autowired
    private UsuarioGateway gateway;

    public Usuario consultarUsuario(String email) {

        log.info("Iniciando a consulta do usuario de email {}", email);

        try {
            return gateway
                    .consultarUsuarioPorEmail(email)
                    .orElseThrow(() -> new UsuarioNaoEncontradoUsecaseException(String.format(ConstanteUtils.ERRO_CONSULTAR_DADO, "usuario")));
        } catch (UsuarioNaoEncontradoUsecaseException ex) {
            log.error("Erro ao realizar consulta de usuario, causa {}", ex.getMessage());

            throw ex;
        }
    }
}
