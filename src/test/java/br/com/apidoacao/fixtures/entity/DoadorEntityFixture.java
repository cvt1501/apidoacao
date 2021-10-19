package br.com.apidoacao.fixtures.entity;

import br.com.apidoacao.gateway.entity.DoadorEntity;
import br.com.apidoacao.gateway.entity.EnderecoEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class DoadorEntityFixture implements TemplateLoader {

    public static final String VALIDO = "valido";

    @Override
    public void load() {
        loadFixturesValido();
    }

    private static void loadFixturesValido() {
        Fixture.of(DoadorEntity.class).addTemplate(VALIDO, new Rule(){
            {
                add("idDoador", random(Integer.class, range(1, 100)));
                add("nome", random("Kevin Cavenatti Bueno", "Isabella Akemi Kimishima Cavenatti"));
                add("cpf", random("12345678910, 1098754321"));
                add("pontos", random(Long.class, range(0L, 10000L)));
                add("endereco", one(EnderecoEntity.class, EnderecoEntityFixture.VALIDO));
            }
        });
    }
}
