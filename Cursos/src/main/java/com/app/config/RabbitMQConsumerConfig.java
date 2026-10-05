package com.app.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConsumerConfig {

	  public static final String EXCHANGE = "alumno.exchange";
	    public static final String QUEUE = "alumno.alta.queue";
	    public static final String ROUTING_KEY = "alumno.alta.routingkey";
	
	    @Bean
	    public Queue queue() {
	        return new Queue(QUEUE, true); // Cola duradera
	    }

	    @Bean
	    public TopicExchange exchange() {
	        return new TopicExchange(EXCHANGE);
	    }

	    @Bean
	    public Binding binding(Queue queue, TopicExchange exchange) {
	        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	    }
   
	    
	
	 @Bean
	 public MessageConverter jsonMessageConverter() {
	    return new Jackson2JsonMessageConverter();
	  }
	 
}