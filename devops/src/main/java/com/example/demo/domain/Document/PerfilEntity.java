package com.example.demo.domain.Document;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Clase que representa una entidad de perfilEntity.
 */
@Data
@Builder
@Document(collection = "profiles")
public class PerfilEntity {
    /**
     * Identificador único de la PerfilEntity.
     */
    @Id
    private String id;

    /**
     * Identificador único del nombre.
     */
    private String nombre;
}

