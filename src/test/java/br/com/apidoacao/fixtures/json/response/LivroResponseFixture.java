package br.com.apidoacao.fixtures.json.response;

import br.com.apidoacao.entrypoint.json.response.LivroResponse;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class LivroResponseFixture implements TemplateLoader {

    public static final String VALIDO = "valido";

    @Override
    public void load() {
        loadFixturesValido();
    }

    private static void loadFixturesValido() {
        Fixture.of(LivroResponse.class).addTemplate(VALIDO, new Rule(){
            {
                add("nome", random("O senhor dos aneis: a sociedade do anel", "Harry Potter: o calice de fogo"));
                add("autor", random("J.R.R Tolkien", "J.K Rowling"));
                add("editora", random("Abril", "Zahar"));
            }
        });
    }
}
