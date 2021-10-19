package br.com.apidoacao.entrypoint.json.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivroRequest {

    @JsonProperty("nome_livro")
    private String nome;

    private String autor;

    private String editora;

}
