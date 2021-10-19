package br.com.apidoacao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Polo {

    private Integer idPolo;

    private String nome;

    private Long codigoPolo;

    private Endereco endereco;

}
