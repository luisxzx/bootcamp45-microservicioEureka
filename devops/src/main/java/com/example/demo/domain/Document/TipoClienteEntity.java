package com.example.demo.domain.Document;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Clase que representa una entidad de TipoClienteEntity.
 */
@Data
@Builder
@Document(collection = "typeClients")
public class TipoClienteEntity {
    /**
     * Identificador único de la TipoClienteEntity.
     */
    @Id
    private String id;

    /**
     * Identificador único del nombre.
     */
    private String nombre;

    /**
     * Identificador único del perfil.
     */
    private String perfilId;
}
