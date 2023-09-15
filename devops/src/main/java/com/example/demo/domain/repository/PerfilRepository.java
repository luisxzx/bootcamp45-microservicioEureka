package com.example.demo.domain.repository;
import com.example.demo.domain.Document.PerfilEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * Interfaz que define un ReactiveMongoRepository para PerfilRepository.
 * */
public interface PerfilRepository extends ReactiveMongoRepository<PerfilEntity, String> {
    /**
     * AccountMongoRepository.
     * @param nombre búsqueda por nombre.
     * @return PerfilEntity.
     */
    Mono<PerfilEntity> findByNombre(String nombre);
}
