package br.com.apidoacao.gateway.impl;

import br.com.apidoacao.domain.Usuario;
import br.com.apidoacao.gateway.UsuarioGateway;
import br.com.apidoacao.gateway.entity.UsuarioEntity;
import br.com.apidoacao.gateway.exception.GatewayException;
import br.com.apidoacao.gateway.mapper.UsuarioMapper;
import br.com.apidoacao.gateway.repository.UsuarioRepository;
import br.com.apidoacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class UsuarioGatewayImpl implements UsuarioGateway {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<Usuario> consultarUsuarioPorEmail(String email) {

        log.info("Iniciando a consulta do usuario de email {}", email);

        try {
            final Optional<UsuarioEntity> optionalEmail = repository.findByEmail(email);

            return optionalEmail
                    .map(mapper::toDomain)
                    .stream()
                    .findFirst();

        } catch (RuntimeException ex) {
            log.error("Erro ao consultar email {}", email);

            throw new GatewayException(String.format(ConstanteUtils.ERRO_CONSULTAR_DADO, "usuario"));
        }
    }

    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {

        log.info("Iniciando cadastro do usuario de email {}", usuario.getEmail());

        try {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

            final UsuarioEntity usuarioEntity = mapper.toEntity(usuario);

            return mapper.toDomain(repository.save(usuarioEntity));
        } catch (RuntimeException ex) {
            log.error("Erro ao cadastrar usuario de email {}, causa {}", usuario.getEmail(), ex.getMessage());

            throw new GatewayException(String.format(ConstanteUtils.ERRO_CADASTRAR_DADO, "usuario"));
        }
    }

}
