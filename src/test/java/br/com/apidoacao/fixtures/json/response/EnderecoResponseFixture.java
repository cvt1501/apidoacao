package br.com.apidoacao.fixtures.json.response;

import br.com.apidoacao.entrypoint.json.response.EnderecoResponse;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class EnderecoResponseFixture implements TemplateLoader {

    public static final String VALIDO = "valido";

    @Override
    public void load() {
        loadFixturesValido();
    }

    private static void loadFixturesValido() {
        Fixture.of(EnderecoResponse.class).addTemplate(VALIDO, new Rule(){
            {
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
