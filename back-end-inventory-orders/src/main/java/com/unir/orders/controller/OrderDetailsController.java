package com.unir.orders.controller;

import com.unir.orders.model.pojo.Order;
import com.unir.orders.model.pojo.OrderDetail;
import com.unir.orders.model.request.CreateOrderDetailRequest;
import com.unir.orders.model.request.CreateOrderRequest;
import com.unir.orders.model.request.UpdateOrderDetailRequest;
import com.unir.orders.model.request.UpdateOrderRequest;
import com.unir.orders.service.concrete.OrderDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Order Detail Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre detalle de órdenes alojados en una base de datos mysql.")
public class OrderDetailsController {
    private final OrderDetailService service;

    @GetMapping("/order-details/order/{id}")
    @Operation(
            operationId = "Obtener detalles de una orden",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de detalles a partir del identificador de una orden.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDetail.class)))
    public ResponseEntity<List<OrderDetail>> getOrderDetailsByOrder(@PathVariable String id) {
        List<OrderDetail> orderDetails = service.findByOrderId(Long.parseLong(id));
        return ResponseEntity.ok(Objects.requireNonNullElse(orderDetails, Collections.emptyList()));
    }

    @GetMapping("/order-details/{id}")
    @Operation(
            operationId = "Obtener un detalle de una orden",
            description = "Operacion de lectura",
            summary = "Se devuelve un detalles a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDetail.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el detalle de orden con el identificador indicado.")
    public ResponseEntity<OrderDetail> getOrderDetail(@PathVariable String id) {
        OrderDetail orderDetail = service.findById(Long.parseLong(id));
        if (orderDetail != null)
            return ResponseEntity.ok(orderDetail);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/order-details/{id}")
    @Operation(
            operationId = "Eliminar un detalle de una orden",
            description = "Operacion de lectura",
            summary = "Se elimina un detalles a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el detalle de orden con el identificador indicado.")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable String id) {
        Boolean removed = service.deleteOrderDetail(Long.parseLong(id));
        if (removed)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/order-details")
    @Operation(
            operationId = "Insertar un detalle de orden",
            description = "Crear un un detalle de orden",
            summary = "Se crea detalle de orden",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Modelo del detalle de órdenes",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateOrderDetailRequest.class))
            )
    )
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDetail.class))
    )
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Datos incorrectos enviados"
    )
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado un detalle de orden con el identificador indicado"
    )
    public ResponseEntity<OrderDetail> addOrderDetail(@RequestBody CreateOrderDetailRequest request) {
        OrderDetail orderDetail = service.createOrderDetail(request);
        if (orderDetail != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(orderDetail);
        else
            return ResponseEntity.badRequest().build();
    }

    @PutMapping("/order-details")
    @Operation(
            operationId = "Modificar un detalle de orden",
            description = "Operacion de escritura",
            summary = "Se modifica un detalle de orden enviando un objeto del tipo detall orden.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto detalle de orden a actualizar",
                    required = true,
                    content = @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = UpdateOrderDetailRequest.class))))
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDetail.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Detalle de orden no encontrado.")
    public ResponseEntity<OrderDetail> updateOrderDetail(@RequestBody UpdateOrderDetailRequest request) {
        OrderDetail orderDetail = service.updateOrderDetail(request);
        if (orderDetail != null)
            return ResponseEntity.ok(orderDetail);
        else
            return ResponseEntity.notFound().build();
    }
}
