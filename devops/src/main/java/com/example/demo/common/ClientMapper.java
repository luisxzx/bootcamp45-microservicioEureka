package com.example.demo.common;
import com.example.demo.domain.Document.ClientEntity;
import com.example.demo.domain.Document.TipoClienteEntity;
import com.example.demo.domain.Document.PerfilEntity;
import com.example.demo.model.Client;
import com.example.demo.model.Perfil;
import com.example.demo.model.TipoCliente;
public class ClientMapper {

    /**
     * Método para ClientMapper.
     * @param clientDto parametro de Client.
     * @return ClientEntity.
     */
    public static ClientEntity dtoToEntity(final Client clientDto) {
        return ClientEntity.builder()
                .id(clientDto.getId())
                .name(clientDto.getName())
                .document(clientDto.getDocumento())
                .idtype(clientDto.getTipoClienteId())
                .build();
    }

    /**
     * Método para ClientMapper.
     * @param typeDto parametro de TipoCliente.
     * @return TipoClienteEntity.
     */
    private static TipoClienteEntity dtoTypeToEntityType(final TipoCliente typeDto) {
        if (typeDto == null) {
            return null;
        }
        return TipoClienteEntity.builder()
                .id(typeDto.getId())
                .nombre(typeDto.getNombre())
                .perfilId(typeDto.getPerfilId())
                .build();
    }

    /**
     * Método para ClientMapper.
     * @param perfilDto parametro de Perfil.
     * @return PerfilEntity.
     */
    private static PerfilEntity dtoPerfilToEntityPerfil(final Perfil perfilDto) {
        if (perfilDto == null) {
            return null;
        }
        return PerfilEntity.builder()
                .id(perfilDto.getId())
                .nombre(perfilDto.getNombre())
                .build();
    }

    /**
     * Método para ClientMapper.
     * @param entity parametro de ClientEntity.
     * @return Client.
     */
    public static Client entityToDto(final ClientEntity entity) {
        return new Client()
                .id(entity.getId())
                .name(entity.getName())
                .documento(entity.getDocument())
                .tipoClienteId(entity.getIdtype());
    }

    /**
     * Método para ClientMapper.
     * @param entityType parametro de TipoClienteEntity.
     * @return TipoCliente.
     */
    public static TipoCliente entityTypeToDtoType(final TipoClienteEntity entityType) {
        if (entityType == null) {
            return null;
        }
        return new TipoCliente()
                .id(entityType.getId())
                .nombre(entityType.getNombre())
                .perfilId(entityType.getPerfilId());
    }

    /**
     * Método para ClientMapper.
     * @param perfilEntity parametro de PerfilEntity.
     * @return Perfil.
     */
    private static Perfil entityPerfilToDtoPerfil(final PerfilEntity perfilEntity) {
        if (perfilEntity == null) {
            return null;
        }
        return new Perfil()
                .id(perfilEntity.getId())
                .nombre(perfilEntity.getNombre());
    }
}
