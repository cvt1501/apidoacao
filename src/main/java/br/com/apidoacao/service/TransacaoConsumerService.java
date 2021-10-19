package br.com.apidoacao.service;

import br.com.apidoacao.service.message.MensagemTransacaoAprovada;
import br.com.apidoacao.usecase.AprovacaoTransacaoOrquestradorUsecase;
import br.com.apidoacao.usecase.AtualizacaoStatusTransacaoUsecase;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransacaoConsumerService {

    @Autowired
    private AprovacaoTransacaoOrquestradorUsecase usecase;

    @Value("${spring.kafka.topics.topic-aprovacao-transacao}")
    private String topico;

    @Value(value = "${spring.kafka.group-id}")
    private String groupId;

    @KafkaListener(topics = "${spring.kafka.topics.topic-aprovacao-transacao}", groupId = "${spring.kafka.group-id}", containerFactory = "transacaoListenerContainerFactory")
    public void listenTransacao(ConsumerRecord<String, MensagemTransacaoAprovada> record) {

        log.info("Mensagem recebida com sucesso!, valor mensagem: {}", record.value());
        log.info("Iniciando a persistencia da mensagem");

        usecase.atualizarInformacoes(record.value());
    }

}
