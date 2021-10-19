package br.com.apidoacao.entrypoint.json.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CadastroUsuarioRequest {

    private String email;

    private String senha;

    private DoadorRequest doador;

}
