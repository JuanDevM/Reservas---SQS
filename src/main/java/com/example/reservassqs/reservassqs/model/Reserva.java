package com.example.reservassqs.reservassqs.model;

import io.swagger.v3.oas.annotations.media.Schema;

public record Reserva(String id,
                      String idhotel,
                      String idhabitacion,
                      String cedulareserva,
                      String fechainicioReserva,
                      String fechafinReserva,
                      String valor,
                      String estado
                     ) {
}
