package br.com.apidoacao.fixtures.json.response;

import br.com.apidoacao.entrypoint.json.response.AutenticarUsuarioResponse;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.UUID;

public class AutenticarUsuarioResponseFixture implements TemplateLoader {

    public static final String VALIDO = "valido";

    @Override
    public void load() {
        loadFixturesValido();
    }

    private static void loadFixturesValido() {
        Fixture.of(AutenticarUsuarioResponse.class).addTemplate(VALIDO, new Rule(){
            {
                add("email", random("kevin_cavenatti@hotmail.com, arq.kimishima@gmail.com"));
                add("token", UUID.randomUUID().toString());
            }
        });
    }
}
