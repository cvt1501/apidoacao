package br.com.apidoacao.fixtures.domain;

import br.com.apidoacao.domain.Endereco;
import br.com.apidoacao.domain.Polo;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PoloFixture implements TemplateLoader {

    public static final String VALIDO = "valido";

    @Override
    public void load() {
        loadFixturesValido();
    }

    private static void loadFixturesValido() {
        Fixture.of(Polo.class).addTemplate(VALIDO, new Rule(){
            {
                add("idPolo", random(Integer.class, range(1, 100)));
                add("nome", random("Posto Vila David, Posto Lago do Taboao"));
                add("codigoPolo", random(Long.class, range(1L, 100L)));
                add("endereco", one(Endereco.class, EnderecoFixture.VALIDO));
            }
        });
    }
}
