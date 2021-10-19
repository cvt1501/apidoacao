package br.com.apidoacao.usecase;

import br.com.apidoacao.domain.Usuario;
import br.com.apidoacao.gateway.UsuarioGateway;
import br.com.apidoacao.usecase.exception.UsuarioNaoEncontradoUsecaseException;
import br.com.apidoacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AutenticacaoUsecase implements UserDetailsService {

    @Autowired
    private UsuarioGateway gateway;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails autenticar(Usuario usuario) {

        log.info("Iniciando a autenticação do usuario de email {}", usuario.getEmail());

        try {
            final UserDetails userDetails = loadUserByUsername(usuario.getEmail());
            final Boolean senhaValidada = passwordEncoder.matches(usuario.getSenha(), userDetails.getPassword());

            if(!senhaValidada) throw new UsuarioNaoEncontradoUsecaseException(ConstanteUtils.ERRO_LOGIN);

            return userDetails;
        } catch (UsuarioNaoEncontradoUsecaseException ex) {
            log.error("Erro na autenticação do usuario, causa {}", ex.getMessage());

            throw ex;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        log.info("Carregando usuario de email {} para autenticação", email);

        Usuario userInfo = gateway
                .consultarUsuarioPorEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(ConstanteUtils.ERRO_LOGIN));

        String[] roles = new String[]{"USER"};

        return User
                .builder()
                .username(userInfo.getEmail())
                .password(userInfo.getSenha())
                .roles(roles)
                .build();
    }

}
