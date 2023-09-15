package com.example.demo.domain.repository;
import com.example.demo.domain.Document.ClientEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * Interfaz que define un ReactiveMongoRepository para ClientRepository.
 * */
public interface ClientRepository extends ReactiveMongoRepository<ClientEntity, String> {
    /**
     * AccountMongoRepository.
     * @param ids búsqueda por id.
     * @return una lista de ClientEntity.
     */
    @Query("{ '_id': { $in: ?0 } }")
    Flux<ClientEntity> findAllByGivenIds(List<ObjectId> ids);
}
