package br.com.apidoacao.entrypoint.json.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoacaoRequest {

    @JsonProperty("codigo_polo")
    private Long codigoPolo;

    private List<LivroRequest> livros;

}
