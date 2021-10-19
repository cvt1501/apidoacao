package br.com.apidoacao.fixtures.domain;

import br.com.apidoacao.domain.Doador;
import br.com.apidoacao.domain.Livro;
import br.com.apidoacao.domain.Polo;
import br.com.apidoacao.domain.Transacao;
import br.com.apidoacao.domain.enums.StatusTransacaoEnum;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.time.LocalDateTime;

public class TransacaoFixture implements TemplateLoader {

    public static final String VALIDO = "valido";

    @Override
    public void load() {
        loadFixturesValido();
    }

    private static void loadFixturesValido() {
        Fixture.of(Transacao.class).addTemplate(VALIDO, new Rule() {
            {
                add("idTransacao", random(Integer.class, range(1, 100)));
                add("pontos", random(Long.class, range(0L, 10000L)));
                add("statusTransacao", random(StatusTransacaoEnum.PENDENTE, StatusTransacaoEnum.CONCLUIDA));
                add("dataTransacao", LocalDateTime.now());
                add("polo", one(Polo.class, PoloFixture.VALIDO));
                add("doador", one(Doador.class, DoadorFixture.VALIDO));
                add("livros", has(2).of(Livro.class, LivroFixture.VALIDO));
            }
        });
    }
}
