package com.paygo.recargas.messaging;

import com.paygo.recargas.entity.Recarga;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecargaPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.exchange.name}")
    private String exchangeName;

    @Value("${app.routing.key}")
    private String routingKey;

    public void publicarRecarga(Recarga recarga) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, recarga);
    }
}
