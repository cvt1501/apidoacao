package br.com.apidoacao.fixtures.entity;

import br.com.apidoacao.gateway.entity.EnderecoEntity;
import br.com.apidoacao.gateway.entity.PoloEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PoloEntityFixture implements TemplateLoader {

    public static final String VALIDO = "valido";

    @Override
    public void load() {
        loadFixturesValido();
    }

    private static void loadFixturesValido() {
        Fixture.of(PoloEntity.class).addTemplate(VALIDO, new Rule(){
            {
                add("idPolo", random(Integer.class, range(1, 100)));
                add("nome", random("Posto Vila David, Posto Lago do Taboao"));
                add("codigoPolo", random(Long.class, range(1L, 100L)));
                add("endereco", one(EnderecoEntity.class, EnderecoEntityFixture.VALIDO));
            }
        });
    }
}
