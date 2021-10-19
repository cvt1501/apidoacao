package br.com.apidoacao.fixtures.json.response;

import br.com.apidoacao.entrypoint.json.response.DoadorResponse;
import br.com.apidoacao.entrypoint.json.response.EnderecoResponse;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class DoadorResponseFixture implements TemplateLoader {

    public static final String VALIDO = "valido";

    @Override
    public void load() {
        loadFixturesValido();
    }

    private static void loadFixturesValido() {
        Fixture.of(DoadorResponse.class).addTemplate(VALIDO, new Rule(){
            {
                add("nome", random("Kevin Cavenatti Bueno", "Isabella Akemi Kimishima Cavenatti"));
                add("cpf", random("12345678910, 1098754321"));
                add("pontos", random(Long.class, range(0L, 10000L)));
                add("endereco", one(EnderecoResponse.class, EnderecoResponseFixture.VALIDO));
            }
        });
    }
}
