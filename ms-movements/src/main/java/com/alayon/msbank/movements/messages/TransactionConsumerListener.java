package com.alayon.msbank.movements.messages;


import com.alayon.msbank.movements.dtos.TransactionRequest;
import com.alayon.msbank.movements.models.MovementModel;
import com.alayon.msbank.movements.service.IMovementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionConsumerListener {

    private final Logger log = LoggerFactory.getLogger(TransactionConsumerListener.class);

    @Autowired
    private IMovementService movementService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void OnMessage(final ConsumerRecord<Integer, String> consumerRecord)
            throws JsonMappingException, JsonProcessingException {

        log.info("****************************************************************");
        log.info("****************************************************************");
        log.info("Consumer Receives in Microservice Account");
        log.info("ConsumerRecord : {}", consumerRecord.value());

        final TransactionRequest requestEvent = objectMapper.readValue(consumerRecord.value(),
                TransactionRequest.class);

        log.info("Id Transaction: {} -  Type: {} - Ammount: {}", requestEvent.getId(), requestEvent.getType(),
                requestEvent.getAmount());

        final MovementModel movementModel = new MovementModel();
        movementModel.setTransactionId(requestEvent.getId());
        movementModel.setAmount(requestEvent.getAmount());
        movementModel.setType(requestEvent.getType());
        movementModel.setAccountId(requestEvent.getAccountId());

        movementService.add(movementModel);

        log.info("Creating new movement {}", movementModel.toString());
        log.info("****************************************************************");
        log.info("****************************************************************");

    }

}