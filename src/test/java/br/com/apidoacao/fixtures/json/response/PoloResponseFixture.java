package br.com.apidoacao.fixtures.json.response;

import br.com.apidoacao.entrypoint.json.response.EnderecoResponse;
import br.com.apidoacao.entrypoint.json.response.PoloResponse;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PoloResponseFixture implements TemplateLoader {

    public static final String VALIDO = "valido";

    @Override
    public void load() {
        loadFixturesValido();
    }

    private static void loadFixturesValido() {
        Fixture.of(PoloResponse.class).addTemplate(VALIDO, new Rule(){
            {
                add("nome", random("Posto Vila David, Posto Lago do Taboao"));
                add("endereco", one(EnderecoResponse.class, EnderecoResponseFixture.VALIDO));
            }
        });
    }
}
