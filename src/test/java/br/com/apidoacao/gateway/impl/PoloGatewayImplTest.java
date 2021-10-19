package br.com.apidoacao.gateway.impl;

import br.com.apidoacao.domain.Polo;
import br.com.apidoacao.fixtures.BaseFixtures;
import br.com.apidoacao.fixtures.entity.PoloEntityFixture;
import br.com.apidoacao.gateway.entity.PoloEntity;
import br.com.apidoacao.gateway.exception.GatewayException;
import br.com.apidoacao.gateway.mapper.PoloMapper;
import br.com.apidoacao.gateway.repository.PoloRepository;
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
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PoloGatewayImplTest {

    @Mock
    private PoloRepository repository;

    @InjectMocks
    private PoloGatewayImpl gateway;

    @Spy
    private PoloMapper mapper = Mappers.getMapper(PoloMapper.class);

    @BeforeAll
    public static void loadFixtures() {
        FixtureFactoryLoader.loadTemplates(BaseFixtures.ENTITY.getPacote());
    }

    @DisplayName("Deve realizar uma consulta e retornar uma lista de polo com sucesso")
    @Test
    public void deveRealizarConsultaDePolos() {

        final List<PoloEntity> listaPoloEntityFixture = Fixture.from(PoloEntity.class).gimme(2, PoloEntityFixture.VALIDO);

        when(repository.findAll()).thenReturn(listaPoloEntityFixture);

        final List<Polo> listaPolo = gateway.consultarPolos();

        assertFalse(listaPolo.isEmpty());
        IntStream.range(0, listaPolo.size()).forEach(position -> {
            assertAll("Deve validar a lista de polo",
                    () -> assertEquals(listaPoloEntityFixture.get(position).getIdPolo(), listaPolo.get(position).getIdPolo()),
                    () -> assertEquals(listaPoloEntityFixture.get(position).getCodigoPolo(), listaPolo.get(position).getCodigoPolo()),
                    () -> assertEquals(listaPoloEntityFixture.get(position).getNome(), listaPolo.get(position).getNome()),
                    () -> assertEquals(listaPoloEntityFixture.get(position).getEndereco().getIdEndereco(), listaPolo.get(position).getEndereco().getIdEndereco()),
                    () -> assertEquals(listaPoloEntityFixture.get(position).getEndereco().getRua(), listaPolo.get(position).getEndereco().getRua()),
                    () -> assertEquals(listaPoloEntityFixture.get(position).getEndereco().getBairro(), listaPolo.get(position).getEndereco().getBairro()),
                    () -> assertEquals(listaPoloEntityFixture.get(position).getEndereco().getNumero(), listaPolo.get(position).getEndereco().getNumero()),
                    () -> assertEquals(listaPoloEntityFixture.get(position).getEndereco().getCidade(), listaPolo.get(position).getEndereco().getCidade()),
                    () -> assertEquals(listaPoloEntityFixture.get(position).getEndereco().getEstado(), listaPolo.get(position).getEndereco().getEstado()),
                    () -> assertEquals(listaPoloEntityFixture.get(position).getEndereco().getCep(), listaPolo.get(position).getEndereco().getCep())
            );
        });
        verify(repository).findAll();
    }

    @DisplayName("Deve realizar uma consulta e retornar um polo com sucesso")
    @Test
    public void deveRealizarConsultaDeUmPolo() {

        final PoloEntity poloEntityFixture = Fixture.from(PoloEntity.class).gimme(PoloEntityFixture.VALIDO);

        when(repository.findByPoloCodigo(anyLong())).thenReturn(Optional.of(poloEntityFixture));

        final Optional<Polo> optionalPolo = gateway.consultarPoloPorCodigo(1L);

        assertFalse(optionalPolo.isEmpty());
        assertAll("Deve validar informações do polo",
                () -> assertEquals(optionalPolo.get().getIdPolo(), poloEntityFixture.getIdPolo()),
                () -> assertEquals(optionalPolo.get().getCodigoPolo(), poloEntityFixture.getCodigoPolo()),
                () -> assertEquals(optionalPolo.get().getNome(), poloEntityFixture.getNome()),
                () -> assertEquals(optionalPolo.get().getEndereco().getIdEndereco(), poloEntityFixture.getEndereco().getIdEndereco()),
                () -> assertEquals(optionalPolo.get().getEndereco().getRua(), poloEntityFixture.getEndereco().getRua()),
                () -> assertEquals(optionalPolo.get().getEndereco().getBairro(), poloEntityFixture.getEndereco().getBairro()),
                () -> assertEquals(optionalPolo.get().getEndereco().getNumero(), poloEntityFixture.getEndereco().getNumero()),
                () -> assertEquals(optionalPolo.get().getEndereco().getCidade(), poloEntityFixture.getEndereco().getCidade()),
                () -> assertEquals(optionalPolo.get().getEndereco().getEstado(), poloEntityFixture.getEndereco().getEstado()),
                () -> assertEquals(optionalPolo.get().getEndereco().getCep(), poloEntityFixture.getEndereco().getCep())
        );
        verify(repository).findByPoloCodigo(anyLong());
    }

    @DisplayName("Deve lançar uma GatewayException cenario lista de polos ao tentar acessar o servidor com problemas")
    @Test
    public void deveLancarUmaGatewayExceptionAoConsultarListaDePolos() {

        when(repository.findAll()).thenThrow(new RuntimeException(String.format(ConstanteUtils.ERRO_CONSULTAR_DADO, "doador")));

        assertThrows(GatewayException.class, () -> gateway.consultarPolos());

        verify(repository).findAll();
    }

    @DisplayName("Deve lançar uma GatewayException ao tentar acessar o servidor com problemas")
    @Test
    public void deveLancarUmaGatewayException() {

        when(repository.findByPoloCodigo(anyLong())).thenThrow(new RuntimeException(String.format(ConstanteUtils.ERRO_CONSULTAR_DADO, "doador")));

        assertThrows(GatewayException.class, () -> gateway.consultarPoloPorCodigo(1L));

        verify(repository).findByPoloCodigo(anyLong());
    }
}
