package br.com.apidoacao.fixtures.entity;

import br.com.apidoacao.gateway.entity.LivroEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class LivroEntityFixture implements TemplateLoader {

    public static final String VALIDO = "valido";

    @Override
    public void load() {
        loadFixturesValido();
    }

    private static void loadFixturesValido() {
        Fixture.of(LivroEntity.class).addTemplate(VALIDO, new Rule(){
            {
                add("idLivro", random(Integer.class, range(1, 100)));
                add("nome", random("O senhor dos aneis: a sociedade do anel", "Harry Potter: o calice de fogo"));
                add("autor", random("J.R.R Tolkien", "J.K Rowling"));
                add("editora", random("Abril", "Zahar"));
            }
        });
    }
}
