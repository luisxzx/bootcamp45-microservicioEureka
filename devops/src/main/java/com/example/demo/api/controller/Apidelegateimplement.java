package com.example.demo.api.controller;
import com.example.demo.application.iClientService;
import com.example.demo.domain.service.ClientService;
import com.example.demo.api.ClientsApiDelegate;
import com.example.demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class Apidelegateimplement implements ClientsApiDelegate {
    /**
     * Para acceder a clientService.
     */
    @Autowired
    private iClientService clientService;

    /**
     * Método para guardar una transacción.
     * @return ClientsApiDelegate.
     */
    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ClientsApiDelegate.super.getRequest();
    }

    /**
     * Método para guardar una transacción.
     * @param clientMono parametro de Client.
     * @return Client.
     */
    @Override
    public Mono<ResponseEntity<Void>> createClient(Mono<Client> clientMono, ServerWebExchange exchange) {
        return clientMono
                .flatMap(clientService::createClient)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.CREATED)))
                .onErrorReturn(new ResponseEntity<Void>(HttpStatus.BAD_REQUEST));
    }




    /**
     * Método para guardar una transacción.
     * @return List<Client>.
     */
    @Override
    public Mono<ResponseEntity<Flux<Client>>> getAllClients(ServerWebExchange exchange) {
        Flux<Client> clientsFlux = clientService.getAllClients();
        return Mono.just(ResponseEntity.ok(clientsFlux));
    }

    /**
     * Método para guardar una transacción.
     * @param clientId variable Client.
     * @return Client.
     */
    @Override
    public Mono<ResponseEntity<Client>> getClientById(String clientId, ServerWebExchange exchange) {
        Mono<Client> clientMono = clientService.getClientById(clientId);
        return clientMono.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Método para guardar una transacción.
     * @param idsFlux variable String.
     * @return Client lista de clinentes.
     */
    @Override
    public Mono<ResponseEntity<Flux<Client>>> bulkRetrieveClients(Flux<String> idsFlux, ServerWebExchange exchange) {
        Flux<Client> clientsFlux = clientService.bulkRetrieveClients(idsFlux);
        return Mono.just(ResponseEntity.ok(clientsFlux));
    }

}
