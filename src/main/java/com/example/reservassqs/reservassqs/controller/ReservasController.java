package com.example.reservassqs.reservassqs.controller;

import com.example.reservassqs.reservassqs.model.Reserva;
import com.example.reservassqs.reservassqs.services.ReservasSQSService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reservas-hoteles")
public class ReservasController {
    ReservasSQSService reservasSQSService;

    private static final String QUEUE_RESERVAS_HOTELES = "reservas-hoteles";

    @PostMapping
    public Mono<String> sendMessageQueue(@RequestBody Reserva reserva) {
        return Mono.just(reservasSQSService.publishStandardQueueMessageReserva(QUEUE_RESERVAS_HOTELES, reserva));
    }
}
