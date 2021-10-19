package br.com.apidoacao.fixtures.domain;

import br.com.apidoacao.domain.Doador;
import br.com.apidoacao.domain.Usuario;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.UUID;

public class UsuarioFixture implements TemplateLoader {

    public static final String VALIDO = "valido";

    @Override
    public void load() {
        loadFixturesValido();
    }

    private static void loadFixturesValido() {
        Fixture.of(Usuario.class).addTemplate(VALIDO, new Rule(){
            {
                add("idUser", random(Integer.class, range(1, 100)));
                add("email", random("kevin_cavenatti@hotmail.com, arq.kimishima@gmail.com"));
                add("senha", UUID.randomUUID().toString());
                add("contaAtiva", random(true, false));
                add("doador", one(Doador.class, DoadorFixture.VALIDO));
            }
        });
    }
}
