package br.com.apidoacao.service.message;

import br.com.apidoacao.domain.enums.StatusTransacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MensagemTransacaoAprovada {

    private Integer idTransacao;

    private StatusTransacaoEnum statusTransacao;

}
