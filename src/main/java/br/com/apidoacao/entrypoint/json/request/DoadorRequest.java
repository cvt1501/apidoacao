package br.com.apidoacao.entrypoint.json.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoadorRequest {

    private String nome;

    private String cpf;

    private EnderecoRequest endereco;

    private Long pontos;

}
