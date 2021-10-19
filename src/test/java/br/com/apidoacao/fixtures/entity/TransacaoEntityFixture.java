package br.com.apidoacao.fixtures.entity;

import br.com.apidoacao.domain.enums.StatusTransacaoEnum;
import br.com.apidoacao.gateway.entity.DoadorEntity;
import br.com.apidoacao.gateway.entity.LivroEntity;
import br.com.apidoacao.gateway.entity.PoloEntity;
import br.com.apidoacao.gateway.entity.TransacaoEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.time.LocalDateTime;

public class TransacaoEntityFixture implements TemplateLoader {

    public static final String VALIDO = "valido";

    @Override
    public void load() {
        loadFixturesValido();
    }

    private static void loadFixturesValido() {
        Fixture.of(TransacaoEntity.class).addTemplate(VALIDO, new Rule(){
            {
                add("idTransacao", random(Integer.class, range(1, 100)));
                add("pontos", random(Long.class, range(0L, 10000L)));
                add("statusTransacao", random("PENDENTE", "CONCLUIDA"));
                add("dataTransacao", LocalDateTime.now());
                add("polo", one(PoloEntity.class, PoloEntityFixture.VALIDO));
                add("doador", one(DoadorEntity.class, DoadorEntityFixture.VALIDO));
                add("livros",  has(2).of(LivroEntity.class, LivroEntityFixture.VALIDO)) ;
            }
        });
    }
}
