package br.com.apidoacao.entrypoint.json.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PoloResponse {

    @JsonProperty("nome_polo")
    private String nome;

    @JsonProperty("endereco_polo")
    private EnderecoResponse endereco;

}