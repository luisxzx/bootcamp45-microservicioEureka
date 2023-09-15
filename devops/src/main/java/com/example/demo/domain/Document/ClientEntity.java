package com.example.demo.domain.Document;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Clase que representa una entidad de ClientEntity.
 */
@Data
@Builder
@Document(collection = "clients")
public class ClientEntity {
    /**
     * Identificador único de la ClientEntity.
     */
    @Id
    private String id;

    /**
     * Identificador único del name.
     */
    private String name;

    /**
     * Identificador único del document.
     */
    private String document;

    /**
     * Identificador único del type.
     */
   private String idtype;
}
