package br.com.apidoacao.entrypoint.json.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id_transacao", "pontos_transacao","status_transacao", "polo", "livros" })
public class TransacaoResponse {

    @JsonProperty("id_transacao")
    private Integer idTransacao;

    @JsonProperty("pontos_transacao")
    private Long pontos;

    @JsonProperty("status_transacao")
    private String statusTransacao;

    private PoloResponse polo;

    private List<LivroResponse> livros;

}
