package br.com.apidoacao.gateway.impl;

import br.com.apidoacao.domain.Doador;
import br.com.apidoacao.fixtures.BaseFixtures;
import br.com.apidoacao.fixtures.entity.DoadorEntityFixture;
import br.com.apidoacao.gateway.entity.DoadorEntity;
import br.com.apidoacao.gateway.exception.GatewayException;
import br.com.apidoacao.gateway.mapper.DoadorMapper;
import br.com.apidoacao.gateway.repository.DoadorRepository;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DoadorGatewayImplTest {

    @Mock
    private DoadorRepository repository;

    @InjectMocks
    private DoadorGatewayImpl gateway;

    @Spy
    private DoadorMapper mapper = Mappers.getMapper(DoadorMapper.class);

    @BeforeAll
    public static void loadFixtures() {
        FixtureFactoryLoader.loadTemplates(BaseFixtures.ENTITY.getPacote());
    }

    @DisplayName("Deve realizar uma consulta e retornar um doador com sucesso")
    @Test
    public void deveRealizarConsultaDoador() {

        final DoadorEntity doadorEntityFixture = Fixture.from(DoadorEntity.class).gimme(DoadorEntityFixture.VALIDO);

        when(repository.findByUserEmail(anyString())).thenReturn(Optional.of(doadorEntityFixture));

        final Optional<Doador> optionalDoador = gateway.consultarDoadorPorEmail("arq.kimishima@gmail.com");

        assertFalse(optionalDoador.isEmpty());
        assertAll("Deve validar doador",
                () -> assertTrue(optionalDoador.isPresent()),
                () -> assertEquals(doadorEntityFixture.getIdDoador(), optionalDoador.get().getIdDoador()),
                () -> assertEquals(doadorEntityFixture.getNome(), optionalDoador.get().getNome()),
                () -> assertEquals(doadorEntityFixture.getCpf(), optionalDoador.get().getCpf()),
                () -> assertEquals(doadorEntityFixture.getEndereco().getIdEndereco(), optionalDoador.get().getEndereco().getIdEndereco()),
                () -> assertEquals(doadorEntityFixture.getEndereco().getRua(), optionalDoador.get().getEndereco().getRua()),
                () -> assertEquals(doadorEntityFixture.getEndereco().getBairro(), optionalDoador.get().getEndereco().getBairro()),
                () -> assertEquals(doadorEntityFixture.getEndereco().getCep(), optionalDoador.get().getEndereco().getCep()),
                () -> assertEquals(doadorEntityFixture.getEndereco().getNumero(), optionalDoador.get().getEndereco().getNumero()),
                () -> assertEquals(doadorEntityFixture.getEndereco().getCidade(), optionalDoador.get().getEndereco().getCidade()),
                () -> assertEquals(doadorEntityFixture.getEndereco().getEstado(), optionalDoador.get().getEndereco().getEstado()),
                () -> assertEquals(doadorEntityFixture.getPontos(), optionalDoador.get().getPontos())
        );
        verify(repository).findByUserEmail(anyString());
    }

    @DisplayName("Deve lançar uma GatewayException ao tentar acessar o servidor com problemas")
    @Test
    public void deveLancarUmaGatewayException() {

        when(repository.findByUserEmail(anyString())).thenThrow(new RuntimeException(String.format(ConstanteUtils.ERRO_CONSULTAR_DADO, "doador")));

        assertThrows(GatewayException.class, () -> gateway.consultarDoadorPorEmail("arq.kimishima@gmail.com"));
        verify(repository).findByUserEmail(anyString());
    }
}
