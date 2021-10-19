package br.com.apidoacao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    private Integer idUser;

    private String email;

    private String senha;

    private Doador doador;

    private Boolean contaAtiva;

}
