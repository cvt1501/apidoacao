package br.com.apidoacao.fixtures.json.response;

import br.com.apidoacao.entrypoint.json.response.CadastroUsuarioResponse;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class CadastrarUsuarioResponseFixture implements TemplateLoader {

    public static final String VALIDO = "valido";

    @Override
    public void load() {
        loadFixturesValido();
    }

    private static void loadFixturesValido() {
        Fixture.of(CadastroUsuarioResponse.class).addTemplate(VALIDO, new Rule(){
            {
                add("mensagem", "Usuario Cadastrado com sucesso!");
            }
        });
    }
}
