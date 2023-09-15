package com.example.demo.application;

import com.example.demo.domain.Document.PerfilEntity;
import com.example.demo.domain.Document.TipoClienteEntity;
import com.example.demo.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface iClientService {

    Mono<Client> createClient(final Client clientDto);

    Mono<TipoClienteEntity> createTipoClienteEntityById(String[] clientType);

    Mono<PerfilEntity> createPerfilEntity(final String nombrePerfil);

    Flux<Client> getAllClients();

    Mono<Client> getClientById(final String id);

    Flux<Client> bulkRetrieveClients(final Flux<String> idsFlux);
}
