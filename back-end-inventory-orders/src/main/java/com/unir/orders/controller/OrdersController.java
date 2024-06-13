package com.unir.orders.controller;

import com.unir.orders.model.pojo.Order;
import com.unir.orders.model.request.CreateOrderRequest;
import com.unir.orders.model.request.UpdateOrderRequest;
import com.unir.orders.service.concrete.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Order Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre órdenes alojados en una base de datos mysql.")
public class OrdersController {

    private final OrderService service;

    @GetMapping("/orders")
    @Operation(
            operationId = "Obtener órdenes",
            description = "Operacion de lectura de órdenes",
            summary = "Se devuelve una lista de todos las órdenes almacenadas en la base de datos, retorna una lista vacía si no se ha registrado órdenes.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class)))
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = service.getOrders();
        return ResponseEntity.ok(Objects.requireNonNullElse(orders, Collections.emptyList()));
    }

    @GetMapping("/orders/{id}")
    @Operation(
            operationId = "Obtener una orden",
            description = "Operacion de lectura",
            summary = "Se devuelve una orden a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado la orden con el identificador indicado.")
    public ResponseEntity<Order> getOrder(@PathVariable String id) {
        Order order = service.getOrderById(Long.parseLong(id));
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/orders/{id}")
    @Operation(
            operationId = "Eliminar una orden",
            description = "Operacion de escritura",
            summary = "Se elimina una orden a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado la orden con el identificador indicado.")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        Boolean removed = service.deleteOrder(Long.parseLong(id));
        if (removed)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/orders")
    @Operation(
            operationId = "Insertar orden",
            description = "Crear una orden y su detalle",
            summary = "Se crea una orden y los detalles de ordenes enviados al crear la orden",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Modelo de la orden y una lista de detalle de órdenes",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateOrderRequest.class))
            )
    )
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class))
    )
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Datos incorrectos enviados"
    )
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado una orden con el identificador indicado"
    )
    public ResponseEntity<Order> addOrder(@RequestBody CreateOrderRequest request) {
        Order order = service.createOrder(request);
        if (order != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(order);
        else
            return ResponseEntity.badRequest().build();
    }

    @PutMapping("/orders")
    @Operation(
            operationId = "Modificar una orden",
            description = "Operacion de escritura",
            summary = "Se modifica una orden enviando un objeto del tipo orden, no es necesario enviar la lista de detalles debido a que se pueden agregar-editar-quitar individualmente al tener la orden creada.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto orden a actualizar",
                    required = true,
                    content = @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = UpdateOrderRequest.class))))
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Orden no encontrada.")
    public ResponseEntity<Order> updateOrder(@RequestBody UpdateOrderRequest request) {
        Order order = service.updateOrder(request);
        if (order != null)
            return ResponseEntity.ok(order);
        else
            return ResponseEntity.notFound().build();
    }
}
