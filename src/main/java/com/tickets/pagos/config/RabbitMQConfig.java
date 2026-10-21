package com.tickets.pagos.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "viaje-events";

    @Bean
    public MessageConverter messageConverter() { //convierte objetos java en json
        return new JacksonJsonMessageConverter();
    } //Para recibir el mensaje desde viajes

    @Bean
    public Queue viajeQueue() { //crea la cola de los mensajes para ser leidos
        return new Queue("viaje-queue", true);
    } //Para recibir el mensaje desde viajes

    @Bean
    public TopicExchange viajeEventsExchange() { //es el que recibe el mensaje y decide donde almacenarlos
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue viajeQueue, TopicExchange viajeEventsExchange) { //une todo
        return BindingBuilder.bind(viajeQueue).to(viajeEventsExchange).with("viaje.creado"); //todo lo que venga con el routingkey viaje.creado debe enviarse a la cola viaje-queue
    }
}
