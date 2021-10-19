package br.com.apidoacao.service;

import br.com.apidoacao.domain.Transacao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransacaoProducerService {

    @Value("${spring.kafka.topics.topic-analise-transacao}")
    private String topico;

    @Autowired
    private KafkaTemplate<String, Transacao> kafkaTemplate;

    public void send(Transacao transacao) {
        kafkaTemplate.send(topico, transacao).addCallback(
                sucess -> log.info("Mensagem com transacao enviada com sucesso {}", sucess.getProducerRecord().value()),
                failure -> log.error("Erro ao enviar mensagem, causa {}", failure.getMessage())
        );
    }

}
