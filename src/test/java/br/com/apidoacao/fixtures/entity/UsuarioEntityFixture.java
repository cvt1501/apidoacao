package br.com.apidoacao.fixtures.entity;

import br.com.apidoacao.gateway.entity.DoadorEntity;
import br.com.apidoacao.gateway.entity.UsuarioEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.UUID;

public class UsuarioEntityFixture implements TemplateLoader {

    public static final String VALIDO = "valido";

    @Override
    public void load() {
        loadFixturesValido();
    }

    private static void loadFixturesValido() {
        Fixture.of(UsuarioEntity.class).addTemplate(VALIDO, new Rule(){
            {
                add("idUser", random(Integer.class, range(1, 100)));
                add("email", random("kevin_cavenatti@hotmail.com, arq.kimishima@gmail.com"));
                add("senha", UUID.randomUUID().toString());
                add("contaAtiva", random(true, false));
                add("doador", one(DoadorEntity.class, DoadorEntityFixture.VALIDO));
            }
        });
    }
}
