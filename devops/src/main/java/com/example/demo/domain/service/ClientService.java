    package com.example.demo.domain.service;
    import com.example.demo.application.iClientService;
    import com.example.demo.domain.Document.ClientEntity;
    import com.example.demo.domain.Document.TipoClienteEntity;
    import com.example.demo.domain.Document.PerfilEntity;
    import com.example.demo.common.ClientMapper;
    import com.example.demo.model.Client;
    import com.example.demo.domain.repository.ClientRepository;
    import com.example.demo.domain.repository.PerfilRepository;
    import com.example.demo.domain.repository.TipoClienteRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.bson.types.ObjectId;
    import reactor.core.publisher.Flux;
    import reactor.core.publisher.Mono;

    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.stream.Collectors;

    @Service
    public class ClientService implements iClientService {

        /**
         * Para ClientRepository de la BD.
         */
        @Autowired
        private ClientRepository clientRepository;

        /**
         * Para TipoClienteRepository de la BD.
         */
        @Autowired
        private TipoClienteRepository tipoClienteRepository;

        /**
         * Para PerfilRepository de la BD.
         */
        @Autowired
        private PerfilRepository perfilRepository;

        private static final Map<String, String[]> clientTypeMap;

        static {
            clientTypeMap = new HashMap<>();
            clientTypeMap.put("1", new String[]{"personal", "vip"});
            clientTypeMap.put("2", new String[]{"empresarial", "pyme"});
        }

        /**
         * Método para guardar una transacción.
         * @param clientDto parametro de Client.
         * @return Client.
         */
        @Override
        public Mono<Client> createClient(final Client clientDto) {
            return Mono.just(clientDto.getTipoClienteId())
                    .map(clientTypeMap::get)
                    .switchIfEmpty(Mono.error(new IllegalArgumentException("El valor del ID de TipoCliente no es válido.")))
                    .flatMap(clientType -> tipoClienteRepository.findByNombre(clientType[0])
                            .switchIfEmpty(createTipoClienteEntityById(clientType))
                    )
                    .flatMap(tipoClienteEntity  -> {
                        String tipoClienteIdAsString = tipoClienteEntity.getId().toString();
                        clientDto.setTipoClienteId(tipoClienteIdAsString);
                        ClientEntity entityToSave = ClientMapper.dtoToEntity(clientDto);
                        return clientRepository.save(entityToSave);
                    })
                    .map(ClientMapper::entityToDto);
        }
        /**
         * Método para guardar una transacción.
         * @param clientType variable String.
         * @return TipoClienteEntity.
         */
        @Override
        public Mono<TipoClienteEntity> createTipoClienteEntityById(String[] clientType) {
            return perfilRepository.findByNombre(clientType[1])
                    .switchIfEmpty(createPerfilEntity(clientType[1]))
                    .flatMap(perfilEntity -> {
                        TipoClienteEntity tipoClienteEntity = TipoClienteEntity.builder()
                                .nombre(clientType[0])
                                .perfilId(perfilEntity.getId())
                                .build();
                        return tipoClienteRepository.save(tipoClienteEntity);
                    });
        }

        /**
         * Método para guardar una transacción.
         * @param nombrePerfil variable String.
         * @return PerfilEntity.
         */
        @Override
        public Mono<PerfilEntity> createPerfilEntity(final String nombrePerfil) {
            PerfilEntity newPerfilEntity = PerfilEntity.builder().nombre(nombrePerfil).build();
            return perfilRepository.save(newPerfilEntity);
        }

        /**
         * Método para guardar una transacción.
         * @return List<Client>.
         */
        @Override
        public Flux<Client> getAllClients() {
            return clientRepository.findAll()
                    .map(ClientMapper::entityToDto);
        }

        /**
         * Método para guardar una transacción.
         * @param id variable strimg.
         * @return Client.
         */
        @Override
        public Mono<Client> getClientById(final String id) {
            return clientRepository.findById(id)
                    .map(ClientMapper::entityToDto);
        }

        /**
         * Método para guardar una transacción.
         * @param idsFlux lista de string.
         * @return Client lista de clinentes.
         */
        @Override
        public Flux<Client> bulkRetrieveClients(final Flux<String> idsFlux) {
            return idsFlux.collectList()
                    .map(ids -> ids.stream().map(id -> id.replaceAll("[\\[\\]\"]", "")).collect(Collectors.toList()))
                    .flatMapMany(cleanedIds -> {
                        List<ObjectId> objectIds = cleanedIds.stream().map(ObjectId::new).collect(Collectors.toList());
                        return clientRepository.findAllByGivenIds(objectIds);
                    })
                    .map(ClientMapper::entityToDto);
        }



    }
