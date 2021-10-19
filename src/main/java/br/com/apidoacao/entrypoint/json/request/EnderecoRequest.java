package br.com.apidoacao.entrypoint.json.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoRequest {

    private String rua;

    private String bairro;

    private Integer numero;

    private String cidade;

    private String estado;

    private String cep;

}
