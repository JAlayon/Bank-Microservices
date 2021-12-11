package com.alayon.msbank.withdrawal.messages;

import com.alayon.msbank.withdrawal.models.TransactionModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

@Component
public class TransactionMessagePublish {

    private final Logger log = LoggerFactory.getLogger(TransactionMessagePublish.class);

    @Value("${spring.kafka.template.default-topic}")
    private String topicName;

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public ListenableFuture<SendResult<Integer, String>> sendDepositEvent(final TransactionModel transactionModel)
            throws JsonProcessingException {
        final Integer key = transactionModel.getId();
        final String value = objectMapper.writeValueAsString(transactionModel);

        final ProducerRecord<Integer, String> producerRecord = buildProducerRecord(key, value, topicName);
        final ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.send(producerRecord);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {

            @Override
            public void onSuccess(final SendResult<Integer, String> result) {

                try {
                    handleSuccess(key, value, result);
                } catch (final JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(final Throwable ex) {
                handleFailure(key, value, ex);

            }
        });
        return listenableFuture;
    }

    private ProducerRecord<Integer, String> buildProducerRecord(final Integer key, final String value,
                                                                final String topic) {
        final List<Header> recordHeaders = List
                .of(new RecordHeader("deposit-subscription-source", "scanner".getBytes()));
        return new ProducerRecord<>(topic, null, key, value, recordHeaders);
    }

    private void handleFailure(final Integer key, final String value, final Throwable ex) {
        log.error("Error: send message and the error is {}", ex.getMessage());
        try {

        } catch (final Throwable throwable) {
            log.error("Error on OnFailure {}", throwable.getMessage());
        }
    }

    private void handleSuccess(final Integer key, final String value, final SendResult<Integer, String> result)
            throws JsonMappingException, JsonProcessingException {

        log.info("Message Sent Successfully for the key :{} and the value is {},partition is {}", key, value,
                result.getRecordMetadata().partition());

    }

}