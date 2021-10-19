package br.com.apidoacao.fixtures.entity;

import br.com.apidoacao.gateway.entity.EnderecoEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class EnderecoEntityFixture implements TemplateLoader {

    public static final String VALIDO = "valido";

    @Override
    public void load() {
        loadFixturesValido();
    }

    private static void loadFixturesValido() {
        Fixture.of(EnderecoEntity.class).addTemplate(VALIDO, new Rule(){
            {
                add("idEndereco", random(Integer.class, range(1, 100)));
                add("rua", random("Rua Italia", "Alameda Quinze de Dezembro"));
                add("bairro", random("Lago do taboao", "Jardim recreio"));
                add("cep", random("06608000", "17720000"));
                add("numero", random(630, 1661));
                add("cidade", "Bragança Paulista");
                add("estado", "São Paulo");
            }
        });
    }
}
