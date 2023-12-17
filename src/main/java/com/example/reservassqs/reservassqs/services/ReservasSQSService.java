package com.example.reservassqs.reservassqs.services;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.example.reservassqs.reservassqs.model.Reserva;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class ReservasSQSService {


    private final AmazonSQS sqsClient;

    private final Environment env;

    private String getQueueUrl(String queueName) {
        return sqsClient.getQueueUrl(queueName).getQueueUrl();
    }

    public String publishStandardQueueMessageReserva(String queueName, Reserva reserva) {

        Map<String, MessageAttributeValue> atributosMensaje = new HashMap<>();
        atributosMensaje.put("id",
                new MessageAttributeValue().withDataType("String").withStringValue(reserva.id()));
        atributosMensaje.put("idhotel",
                new MessageAttributeValue().withDataType("String").withStringValue(reserva.idhotel()));
        atributosMensaje.put("idhabitacion",
                new MessageAttributeValue().withDataType("String").withStringValue(reserva.idhabitacion()));
        atributosMensaje.put("cedulareserva",
                new MessageAttributeValue().withDataType("String").withStringValue(reserva.cedulareserva()));
        atributosMensaje.put("fechainicioReserva",
                new MessageAttributeValue().withDataType("String").withStringValue(reserva.fechainicioReserva()));
        atributosMensaje.put("fechafinReserva",
                new MessageAttributeValue().withDataType("String").withStringValue(reserva.fechafinReserva()));
        atributosMensaje.put("valor",
                new MessageAttributeValue().withDataType("String").withStringValue(reserva.valor()));
        atributosMensaje.put("estado",
                new MessageAttributeValue().withDataType("String").withStringValue(reserva.estado()));

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(getQueueUrl(queueName))
                .withMessageBody(reserva.id())
                .withDelaySeconds(Integer.parseInt(env.getProperty("aws.sqs.delay-seconds")))
                .withMessageAttributes(atributosMensaje);

        return sqsClient.sendMessage(sendMessageRequest).getMessageId();
    }
}
