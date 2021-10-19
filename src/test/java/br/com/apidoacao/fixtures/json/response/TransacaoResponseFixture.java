package br.com.apidoacao.fixtures.json.response;

import br.com.apidoacao.entrypoint.json.response.DoadorResponse;
import br.com.apidoacao.entrypoint.json.response.LivroResponse;
import br.com.apidoacao.entrypoint.json.response.PoloResponse;
import br.com.apidoacao.entrypoint.json.response.TransacaoResponse;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class TransacaoResponseFixture implements TemplateLoader {

    public static final String VALIDO = "valido";

    @Override
    public void load() {
        loadFixturesValido();
    }

    private static void loadFixturesValido() {
        Fixture.of(TransacaoResponse.class).addTemplate(VALIDO, new Rule() {
            {
                add("pontos", random(Long.class, range(0L, 10000L)));
                add("concluida", random(true, false));
                add("polo", one(PoloResponse.class, PoloResponseFixture.VALIDO));
                add("doador", one(DoadorResponse.class, DoadorResponseFixture.VALIDO));
                add("livros", has(2).of(LivroResponse.class, LivroResponseFixture.VALIDO));
            }
        });
    }
}
