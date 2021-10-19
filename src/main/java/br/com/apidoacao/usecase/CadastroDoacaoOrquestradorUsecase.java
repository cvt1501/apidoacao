package br.com.apidoacao.usecase;

import br.com.apidoacao.domain.Doador;
import br.com.apidoacao.domain.Polo;
import br.com.apidoacao.domain.Transacao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class CadastroDoacaoOrquestradorUsecase {

    @Autowired
    private ConsultaDoadorUsecase consultaDoadorUsecase;

    @Autowired
    private ConsultaPoloPorCodigoUsecase consultaPoloUsecase;

    @Autowired
    private CadastroTransacaoUsecase cadastroTransacaoUsecase;

    public Transacao cadastrarDoacao(String email, Transacao transacao) {

        log.info("Iniciando o cadastro da doação");

        final Doador doador = consultaDoadorUsecase.consultarPorEmail(email);
        final Polo polo = consultaPoloUsecase.consultaPorCodigo(transacao.getPolo().getCodigoPolo());

        final Transacao transacaoProcessada = Transacao
                .builder()
                .doador(doador)
                .polo(polo)
                .livros(transacao.getLivros())
                .dataTransacao(LocalDateTime.now())
                .build();

        return cadastroTransacaoUsecase.cadastrarTransacao(transacaoProcessada);
    }

}
