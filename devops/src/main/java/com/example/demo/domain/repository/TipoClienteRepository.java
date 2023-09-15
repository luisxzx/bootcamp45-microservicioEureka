package com.example.demo.domain.repository;
import com.example.demo.domain.Document.TipoClienteEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * Interfaz que define un ReactiveMongoRepository para TipoClienteRepository.
 * */
public interface TipoClienteRepository extends ReactiveMongoRepository<TipoClienteEntity, String> {
    /**
     * AccountMongoRepository.
     * @param nombre búsqueda por nombre.
     * @return TipoClienteEntity.
     */
    Mono<TipoClienteEntity> findByNombre(String nombre);
}
