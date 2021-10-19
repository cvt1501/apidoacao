package br.com.apidoacao.gateway.impl;

import br.com.apidoacao.domain.Transacao;
import br.com.apidoacao.fixtures.BaseFixtures;
import br.com.apidoacao.fixtures.domain.TransacaoFixture;
import br.com.apidoacao.fixtures.entity.TransacaoEntityFixture;
import br.com.apidoacao.gateway.entity.TransacaoEntity;
import br.com.apidoacao.gateway.exception.GatewayException;
import br.com.apidoacao.gateway.mapper.TransacaoMapper;
import br.com.apidoacao.gateway.repository.TransacaoRepository;
import br.com.apidoacao.utils.ConstanteUtils;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransacaoGatewayImplTest {

    @Mock
    private TransacaoRepository repository;

    @InjectMocks
    private TransacaoGatewayImpl gateway;

    @Spy
    private TransacaoMapper mapper = Mappers.getMapper(TransacaoMapper.class);

    @BeforeAll
    public static void loadFixtures() {
        FixtureFactoryLoader.loadTemplates(BaseFixtures.ALL.getPacote());
    }

    @DisplayName("Deve consultar transacoes por email do usuario")
    @Test
    public void deveConsultarTransacoes() {

        final List<TransacaoEntity> transacoesFixture = Fixture.from(TransacaoEntity.class).gimme(2, TransacaoEntityFixture.VALIDO);
        
        when(repository.findByUserEmail(anyString())).thenReturn(transacoesFixture);

        final List<Transacao> transacoes = gateway.consultarTransacoesPorEmail("arq.kimishima@gmai.com");

        assertFalse(transacoes.isEmpty());
        IntStream.range(0, transacoes.size()).forEach(position -> {
            assertAll("Deve validar transacoes",
                    () -> assertEquals(transacoesFixture.get(position).getIdTransacao(), transacoes.get(position).getIdTransacao()),
                    () -> assertEquals(transacoesFixture.get(position).getDataTransacao(), transacoes.get(position).getDataTransacao()),
                    () -> assertEquals(transacoesFixture.get(position).getStatusTransacao(), transacoes.get(position).getStatusTransacao().toString()),
                    () -> assertEquals(transacoesFixture.get(position).getPontos(), transacoes.get(position).getPontos()),
                    () -> assertEquals(transacoesFixture.get(position).getPolo().getIdPolo(), transacoes.get(position).getPolo().getIdPolo()),
                    () -> assertEquals(transacoesFixture.get(position).getPolo().getNome(), transacoes.get(position).getPolo().getNome()),
                    () -> assertEquals(transacoesFixture.get(position).getPolo().getEndereco().getIdEndereco(), transacoes.get(position).getPolo().getEndereco().getIdEndereco()),
                    () -> assertEquals(transacoesFixture.get(position).getPolo().getEndereco().getRua(), transacoes.get(position).getPolo().getEndereco().getRua()),
                    () -> assertEquals(transacoesFixture.get(position).getPolo().getEndereco().getBairro(), transacoes.get(position).getPolo().getEndereco().getBairro()),
                    () -> assertEquals(transacoesFixture.get(position).getPolo().getEndereco().getCep(), transacoes.get(position).getPolo().getEndereco().getCep()),
                    () -> assertEquals(transacoesFixture.get(position).getPolo().getEndereco().getNumero(), transacoes.get(position).getPolo().getEndereco().getNumero()),
                    () -> assertEquals(transacoesFixture.get(position).getPolo().getEndereco().getCidade(), transacoes.get(position).getPolo().getEndereco().getCidade()),
                    () -> assertEquals(transacoesFixture.get(position).getPolo().getEndereco().getEstado(), transacoes.get(position).getPolo().getEndereco().getEstado()),
                    () -> assertEquals(transacoesFixture.get(position).getDoador().getIdDoador(), transacoes.get(position).getDoador().getIdDoador()),
                    () -> assertEquals(transacoesFixture.get(position).getDoador().getNome(), transacoes.get(position).getDoador().getNome()),
                    () -> assertEquals(transacoesFixture.get(position).getDoador().getPontos(), transacoes.get(position).getDoador().getPontos()),
                    () -> assertEquals(transacoesFixture.get(position).getDoador().getEndereco().getIdEndereco(), transacoes.get(position).getDoador().getEndereco().getIdEndereco()),
                    () -> assertEquals(transacoesFixture.get(position).getDoador().getEndereco().getRua(), transacoes.get(position).getDoador().getEndereco().getRua()),
                    () -> assertEquals(transacoesFixture.get(position).getDoador().getEndereco().getBairro(), transacoes.get(position).getDoador().getEndereco().getBairro()),
                    () -> assertEquals(transacoesFixture.get(position).getDoador().getEndereco().getCep(), transacoes.get(position).getDoador().getEndereco().getCep()),
                    () -> assertEquals(transacoesFixture.get(position).getDoador().getEndereco().getNumero(), transacoes.get(position).getDoador().getEndereco().getNumero()),
                    () -> assertEquals(transacoesFixture.get(position).getDoador().getEndereco().getCidade(), transacoes.get(position).getDoador().getEndereco().getCidade()),
                    () -> assertEquals(transacoesFixture.get(position).getDoador().getEndereco().getEstado(), transacoes.get(position).getDoador().getEndereco().getEstado())
            );

            IntStream.range(0, transacoes.get(position).getLivros().size()).forEach(positionLivro -> {
                assertAll("Deve validar livros transacoes",
                        () -> assertEquals(transacoesFixture.get(position).getLivros().get(positionLivro).getIdLivro(), transacoes.get(position).getLivros().get(positionLivro).getIdLivro()),
                        () -> assertEquals(transacoesFixture.get(position).getLivros().get(positionLivro).getNome(), transacoes.get(position).getLivros().get(positionLivro).getNome()),
                        () -> assertEquals(transacoesFixture.get(position).getLivros().get(positionLivro).getAutor(), transacoes.get(position).getLivros().get(positionLivro).getAutor()),
                        () -> assertEquals(transacoesFixture.get(position).getLivros().get(positionLivro).getEditora(), transacoes.get(position).getLivros().get(positionLivro).getEditora())
                );
            });
        });
        verify(repository).findByUserEmail(anyString());
    }

    @DisplayName("Deve lancar exception ao realizar consulta de transacoes")
    @Test
    public void deveRealizarExceptionAoRealizarConsulta() {

        when(repository.findByUserEmail(anyString())).thenThrow(new RuntimeException(String.format(ConstanteUtils.ERRO_CONSULTAR_DADO, "transacao")));

        assertThrows(GatewayException.class, () -> gateway.consultarTransacoesPorEmail("arq.kimishima@gmail.com"));
        verify(repository).findByUserEmail(anyString());
    }

    @DisplayName("Deve realizar um cadastro de transacao com sucesso")
    @Test
    public void deveCadastrarTransacao() {

        final TransacaoEntity transacaoFixture = Fixture.from(TransacaoEntity.class).gimme(TransacaoEntityFixture.VALIDO);
        final Transacao transacaoDomainFixture = Fixture.from(Transacao.class).gimme(TransacaoFixture.VALIDO);

        when(repository.save(any())).thenReturn(transacaoFixture);

        final Transacao transacao = gateway.cadastrarTransacao(transacaoDomainFixture);

        assertAll("Deve validar transacoes",
                () -> assertEquals(transacaoFixture.getIdTransacao(), transacao.getIdTransacao()),
                () -> assertEquals(transacaoFixture.getDataTransacao(), transacao.getDataTransacao()),
                () -> assertEquals(transacaoFixture.getStatusTransacao(), transacao.getStatusTransacao().toString()),
                () -> assertEquals(transacaoFixture.getPontos(), transacao.getPontos()),
                () -> assertEquals(transacaoFixture.getPolo().getIdPolo(), transacao.getPolo().getIdPolo()),
                () -> assertEquals(transacaoFixture.getPolo().getNome(), transacao.getPolo().getNome()),
                () -> assertEquals(transacaoFixture.getPolo().getEndereco().getIdEndereco(), transacao.getPolo().getEndereco().getIdEndereco()),
                () -> assertEquals(transacaoFixture.getPolo().getEndereco().getRua(), transacao.getPolo().getEndereco().getRua()),
                () -> assertEquals(transacaoFixture.getPolo().getEndereco().getBairro(), transacao.getPolo().getEndereco().getBairro()),
                () -> assertEquals(transacaoFixture.getPolo().getEndereco().getCep(), transacao.getPolo().getEndereco().getCep()),
                () -> assertEquals(transacaoFixture.getPolo().getEndereco().getNumero(), transacao.getPolo().getEndereco().getNumero()),
                () -> assertEquals(transacaoFixture.getPolo().getEndereco().getCidade(), transacao.getPolo().getEndereco().getCidade()),
                () -> assertEquals(transacaoFixture.getPolo().getEndereco().getEstado(), transacao.getPolo().getEndereco().getEstado()),
                () -> assertEquals(transacaoFixture.getDoador().getIdDoador(), transacao.getDoador().getIdDoador()),
                () -> assertEquals(transacaoFixture.getDoador().getNome(), transacao.getDoador().getNome()),
                () -> assertEquals(transacaoFixture.getDoador().getPontos(), transacao.getDoador().getPontos()),
                () -> assertEquals(transacaoFixture.getDoador().getEndereco().getIdEndereco(), transacao.getDoador().getEndereco().getIdEndereco()),
                () -> assertEquals(transacaoFixture.getDoador().getEndereco().getRua(), transacao.getDoador().getEndereco().getRua()),
                () -> assertEquals(transacaoFixture.getDoador().getEndereco().getBairro(), transacao.getDoador().getEndereco().getBairro()),
                () -> assertEquals(transacaoFixture.getDoador().getEndereco().getCep(), transacao.getDoador().getEndereco().getCep()),
                () -> assertEquals(transacaoFixture.getDoador().getEndereco().getNumero(), transacao.getDoador().getEndereco().getNumero()),
                () -> assertEquals(transacaoFixture.getDoador().getEndereco().getCidade(), transacao.getDoador().getEndereco().getCidade()),
                () -> assertEquals(transacaoFixture.getDoador().getEndereco().getEstado(), transacao.getDoador().getEndereco().getEstado())
        );

        IntStream.range(0, transacao.getLivros().size()).forEach(positionLivro -> {
            assertAll("Deve validar livros transacoes",
                    () -> assertEquals(transacaoFixture.getLivros().get(positionLivro).getIdLivro(), transacao.getLivros().get(positionLivro).getIdLivro()),
                    () -> assertEquals(transacaoFixture.getLivros().get(positionLivro).getNome(), transacao.getLivros().get(positionLivro).getNome()),
                    () -> assertEquals(transacaoFixture.getLivros().get(positionLivro).getAutor(), transacao.getLivros().get(positionLivro).getAutor()),
                    () -> assertEquals(transacaoFixture.getLivros().get(positionLivro).getEditora(), transacao.getLivros().get(positionLivro).getEditora())
            );
        });
        verify(repository).save(any());
    }

    @DisplayName("Deve lancar exception ao cadastrar transacao")
    @Test
    public void deveRealizarExceptionAoCadastrarTransacao() {

        final Transacao transacaoDomainFixture = Fixture.from(Transacao.class).gimme(TransacaoFixture.VALIDO);

        when(repository.save(any())).thenThrow(new RuntimeException(String.format(ConstanteUtils.ERRO_CADASTRAR_DADO, "transacao")));

        assertThrows(GatewayException.class, () -> gateway.cadastrarTransacao(transacaoDomainFixture));
        verify(repository).save(any());
    }
}
