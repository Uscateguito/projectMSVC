package com.microservice.service.controller;

import com.microservice.service.model.AlojamientoModel;
import com.microservice.service.model.ClientModel;
import com.microservice.service.model.ProviderModel;
import com.microservice.service.model.TransportModel;
import com.microservice.service.model.inputs.AlojamientoInput;
import com.microservice.service.model.inputs.ClienteInput;
import com.microservice.service.model.inputs.ProveedorInput;
import com.microservice.service.model.inputs.TransporteInput;
import com.microservice.service.model.intermedias.Alojamiento_Persona;
import com.microservice.service.model.intermedias.Alojamiento_Proveedor;
import com.microservice.service.model.intermedias.Transporte_Persona;
import com.microservice.service.model.intermedias.Transporte_Proveedor;
import com.microservice.service.repository.*;
import org.slf4j.Logger;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class GraphQlController {

    private final AlojamientoRepository alojamientoRepository;
    private final Alojamiento_PersonaRepository alojamiento_personaRepository;
    private final Alojamiento_ProveedorRepository alojamiento_proveedorRepository;
    private final TransporteRepository transporteRepository;
    private final Transporte_PersonaRepository transporte_personaRepository;
    private final Transporte_ProveedorRepository transporte_proveedorRepository;
    private final ClienteRepository clienteRepository;
    private final ProveedorRepository proveedorRepository;
    private final Alojamiento_PersonaRepository alojamiento_PersonaRepository;

    Logger log = org.slf4j.LoggerFactory.getLogger(GraphQlController.class);

    public GraphQlController(AlojamientoRepository alojamientoRepository, Alojamiento_PersonaRepository alojamientoPersona, Alojamiento_ProveedorRepository alojamientoProveedor, Alojamiento_ProveedorRepository alojamientoProveedorRepository, TransporteRepository transporteRepository, Transporte_PersonaRepository transportePersona, Alojamiento_PersonaRepository alojamientoPersonaRepository1, Transporte_PersonaRepository transportePersonaRepository, Transporte_ProveedorRepository transporteProveedor, Transporte_ProveedorRepository transporteProveedorRepository, ClienteRepository clienteRepository, ProveedorRepository proveedorRepository, Alojamiento_PersonaRepository alojamientoPersonaRepository) {
        this.alojamientoRepository = alojamientoRepository;
        alojamiento_proveedorRepository = alojamientoProveedorRepository;
        alojamiento_personaRepository = alojamientoPersonaRepository1;
        this.transporteRepository = transporteRepository;
        transporte_personaRepository = transportePersonaRepository;
        transporte_proveedorRepository = transporteProveedorRepository;
        this.clienteRepository = clienteRepository;
        this.proveedorRepository = proveedorRepository;
        alojamiento_PersonaRepository = alojamientoPersonaRepository;
    }

//    START QUERY MAPPINGS

    @QueryMapping
    Iterable<AlojamientoModel> alojamientos() {
        return alojamientoRepository.findAll();
    }

    @QueryMapping
    Iterable<TransportModel> transportes() {
        return transporteRepository.findAll();
    }

    @QueryMapping
    Iterable<ClientModel> clientes() {
        return clienteRepository.findAll();
    }

    @QueryMapping
    Iterable<ProviderModel> proveedores() {
        return proveedorRepository.findAll();
    }

    @QueryMapping
    Optional<AlojamientoModel> alojamientoById(@Argument String id) {
        return alojamientoRepository.findById(id);
    }

    @QueryMapping
    Optional<TransportModel> transporteById(@Argument String id) {
        return transporteRepository.findById(id);
    }

    @QueryMapping
    Optional<ClientModel> clienteByCorreo(@Argument String correo) {
        return Optional.ofNullable(clienteRepository.findByCorreo(correo));
    }

    @QueryMapping
    Optional<ProviderModel> proveedorByCorreo(@Argument String correo) {
        return Optional.ofNullable(proveedorRepository.findByCorreo(correo));
    }

    @QueryMapping
    Iterable<Alojamiento_Persona> alojamientosByCliente(@Argument String clienteCorreo) {
        return alojamiento_PersonaRepository.findAllByCliente_Correo(clienteCorreo);
    }

    @QueryMapping
    int countAlojamientosByCliente(@Argument String clienteCorreo) {
        return alojamiento_PersonaRepository.countAllByCliente_Correo(clienteCorreo);
    }

    @QueryMapping
    Iterable<Transporte_Persona> transportesByCliente(@Argument String clienteCorreo) {
        return transporte_personaRepository.findAllByCliente_Correo(clienteCorreo);
    }

    @QueryMapping
    int countTransportesByCliente(@Argument String clienteCorreo) {
        return transporte_personaRepository.countAllByCliente_Correo(clienteCorreo);
    }

    @QueryMapping
    Iterable<AlojamientoModel> alojamientosByProveedor(@Argument String proveedorCorreo) {
        return alojamientoRepository.findAllByProveedor_Correo(proveedorCorreo);
    }

    @QueryMapping
    int countAlojamientosByProveedor(@Argument String proveedorCorreo) {
        return alojamiento_proveedorRepository.countAllByProveedor_Correo(proveedorCorreo);
    }

    @QueryMapping
    Iterable<TransportModel> transportesByProveedor(@Argument String proveedorCorreo) {
        return transporteRepository.findAllByProveedor_Correo(proveedorCorreo);
    }

    @QueryMapping
    int countTransportesByProveedor(@Argument String proveedorCorreo) {
        return transporte_proveedorRepository.countAllByProveedor_Correo(proveedorCorreo);
    }

//    Querys especiales

    @QueryMapping
    Iterable<Alojamiento_Persona> infoAlojamiento_persona(@Argument String alojamientoId) {
        return alojamiento_PersonaRepository.findAllByAlojamiento_Id(alojamientoId);
    }

    @QueryMapping
    Iterable<Transporte_Persona> infoTransporte_persona(@Argument String transporteId) {
        return transporte_personaRepository.findAllByTransporte_Id(transporteId);
    }


//    END QUERY MAPPINGS


//    START MUTATION MAPPINGS

    @MutationMapping
    ClientModel addCliente(@Argument ClienteInput cliente) {

        ClientModel client = new ClientModel();
        return getClientModel(cliente, client);
    }

    @MutationMapping
    ProviderModel addProveedor(@Argument ProveedorInput proveedor) {
        return getProviderModel(proveedor, new ProviderModel());
    }

    @MutationMapping
    AlojamientoModel addAlojamiento(@Argument AlojamientoInput alojamiento) {
        return getAlojamientoModel(alojamiento, new AlojamientoModel(), proveedorRepository.findByCorreo(alojamiento.proveedorCorreo().get()));
    }

    @MutationMapping
    TransportModel addTransporte(@Argument TransporteInput transporte) {

        return getTransportModel(transporte, new TransportModel(), proveedorRepository.findByCorreo(transporte.proveedorCorreo().get()));
    }

    @MutationMapping
    Alojamiento_Persona rentAlojamiento(
            @Argument String alojamientoId,
            @Argument String personaCorreo,
            @Argument String fechaCheckIn,
            @Argument String fechaCheckOut
    ) {
        try {
            AlojamientoModel alojamiento = alojamientoRepository.findById(alojamientoId).orElse(null);
            ClientModel persona = clienteRepository.findByCorreo(personaCorreo);

            return alojamiento_PersonaRepository.save(
                    Alojamiento_Persona.builder()
                            .fechaCheckIn(fechaCheckIn)
                            .fechaCheckOut(fechaCheckOut)
                            .alojamiento(alojamiento)
                            .cliente(persona)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Alojamiento o correo de cliente no encontrado");
        }
    }

    @MutationMapping
    Alojamiento_Proveedor addAlojamientoProveedor(
            @Argument String alojamientoId,
            @Argument String proveedorCorreo
    ) {
        try {
            AlojamientoModel alojamiento = alojamientoRepository.findById(alojamientoId).orElse(null);
            ProviderModel proveedor = proveedorRepository.findByCorreo(proveedorCorreo);

            return alojamiento_proveedorRepository.save(
                    Alojamiento_Proveedor.builder()
                            .alojamiento(alojamiento)
                            .proveedor(proveedor)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Alojamiento o correo de proveedor no encontrado");
        }
    }

    @MutationMapping
    Transporte_Persona rentTransporte(
            @Argument String transporteId,
            @Argument String personaCorreo,
            @Argument String numeroPlaca
    ) {
        try {
            TransportModel transporte = transporteRepository.findById(transporteId).orElse(null);
            ClientModel persona = clienteRepository.findByCorreo(personaCorreo);

            return transporte_personaRepository.save(
                    Transporte_Persona.builder()
                            .numeroPlaca(numeroPlaca)
                            .transporte(transporte)
                            .cliente(persona)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Transporte o correo de cliente no encontrado");
        }

    }

    @MutationMapping
    Transporte_Proveedor addTransporteProveedor(
            @Argument String transporteId,
            @Argument String proveedorCorreo
    ) {
        try {
            TransportModel transporte = transporteRepository.findById(transporteId).orElse(null);
            ProviderModel proveedor = proveedorRepository.findByCorreo(proveedorCorreo);

            return transporte_proveedorRepository.save(
                    Transporte_Proveedor.builder()
                            .transporte(transporte)
                            .proveedor(proveedor)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Transporte o correo de proveedor no encontrado");
        }

    }

    @MutationMapping
    ClientModel updateClienteData(
            @Argument String correo,
            @Argument ClienteInput cliente
    ) {
        try {
            ClientModel client = clienteRepository.findByCorreo(correo);
            return getClientModel(cliente, client);
        } catch (Exception e) {
            throw new RuntimeException("Cliente no encontrado");
        }
    }

    @MutationMapping
    ProviderModel updateProveedorData(
            @Argument String correo,
            @Argument ProveedorInput proveedor
    ) {
        try {
            ProviderModel provider = proveedorRepository.findByCorreo(correo);
            return getProviderModel(proveedor, provider);
        } catch (Exception e) {
            throw new RuntimeException("Proveedor no encontrado");
        }
    }

    @MutationMapping
    AlojamientoModel updateAlojamientoData(
            @Argument String id,
            @Argument AlojamientoInput alojamiento
    ) {
        try {
            AlojamientoModel aloj = alojamientoRepository.findById(id).orElseThrow(() -> new RuntimeException("Alojamiento no encontrado"));
            ProviderModel proveedor = proveedorRepository.findByCorreo(alojamiento.proveedorCorreo().get());

            return getAlojamientoModel(alojamiento, aloj, proveedor);
        } catch (Exception e) {
            throw new RuntimeException("Alojamiento no encontrado");
        }
    }

    @MutationMapping
    TransportModel updateTransporteData(
            @Argument String id,
            @Argument TransporteInput transporte
    ) {
        try{
        TransportModel transporteFinal = transporteRepository.findById(id).orElseThrow(() -> new RuntimeException("Transporte no encontrado"));
        ProviderModel proveedor = proveedorRepository.findByCorreo(transporte.proveedorCorreo().get());

        return getTransportModel(transporte, transporteFinal, proveedor);
        } catch (Exception e) {
            throw new RuntimeException("Transporte no encontrado");
        }

    }

    @MutationMapping
    Alojamiento_Persona CalificacionYComentarioAlojamiento(
            @Argument String alojamientoId,
            @Argument String clienteCorreo,
            @Argument Float calificacion,
            @Argument String comentario
    ) {
        try {
            AlojamientoModel alojamiento = alojamientoRepository.findById(alojamientoId).orElse(null);
            ClientModel cliente = clienteRepository.findByCorreo(clienteCorreo);
            assert alojamiento != null;
            Alojamiento_Persona alojamiento_persona = alojamiento_PersonaRepository.findByAlojamiento_IdAndCliente_Correo(alojamiento.getId(), cliente.getCorreo());

            alojamiento_persona.setComentario(comentario);
            alojamiento_persona.setCalificacion(calificacion);

            return alojamiento_PersonaRepository.save(
                    alojamiento_persona
            );
        } catch (Exception e) {
            throw new RuntimeException("Alojamiento o correo de cliente no encontrado");
        }
    }

    @MutationMapping
    Transporte_Persona CalificacionYComentarioTransporte(
            @Argument String transporteId,
            @Argument String clienteCorreo,
            @Argument Float calificacion,
            @Argument String comentario
    ) {
        try {
            TransportModel transporte = transporteRepository.findById(transporteId).orElse(null);
            ClientModel cliente = clienteRepository.findByCorreo(clienteCorreo);
            assert transporte != null;
            Transporte_Persona transporte_persona = transporte_personaRepository.findByTransporte_IdAndCliente_Correo(transporte.getId(), cliente.getCorreo());

            transporte_persona.setComentario(comentario);
            transporte_persona.setCalificacion(calificacion);

            return transporte_personaRepository.save(
                    transporte_persona
            );
        } catch (Exception e) {
            throw new RuntimeException("Transporte o correo de cliente no encontrado");
        }
    }

//    DELETE MAPPINGS

    @MutationMapping
    void deleteAlojamiento(@Argument String id) {
        alojamientoRepository.deleteById(id);
    }

    @MutationMapping
    void deleteTransporte(@Argument String id) {
        transporteRepository.deleteById(id);
    }

    @MutationMapping
    boolean deleteCliente(@Argument String correo) {
        return clienteRepository.deleteByCorreo(correo);
    }

    @MutationMapping
    boolean deleteProveedor(@Argument String correo) {
        return proveedorRepository.deleteByCorreo(correo);
    }

//    MUTATIONS CON OPERACIONES:

    @MutationMapping
    float updateCalificacionAlojamiento(@Argument String alojamientoId) {
        try {
            AlojamientoModel alojamiento = alojamientoRepository.findById(alojamientoId).orElse(null);
            assert alojamiento != null;
            float calificacion = alojamiento_PersonaRepository.findAllByAlojamiento_Id(alojamientoId).stream().map(Alojamiento_Persona::getCalificacion).reduce(0f, Float::sum) / alojamiento_PersonaRepository.countAllByAlojamiento_Id(alojamientoId);
            alojamiento.setCalificacion(calificacion);
            alojamientoRepository.save(alojamiento);
            return calificacion;
        } catch (Exception e) {
            throw new RuntimeException("Alojamiento no encontrado");
        }
    }

    @MutationMapping
    float updateCalificacionTransporte(@Argument String transporteId) {
        try {
            TransportModel transporte = transporteRepository.findById(transporteId).orElse(null);
            assert transporte != null;
            float calificacion = transporte_personaRepository.findAllByTransporte_Id(transporteId).stream().map(Transporte_Persona::getCalificacion).reduce(0f, Float::sum) / transporte_personaRepository.countAllByTransporte_Id(transporteId);
            transporte.setCalificacion(calificacion);
            transporteRepository.save(transporte);
            return calificacion;
        } catch (Exception e) {
            throw new RuntimeException("Transporte no encontrado");
        }
    }


//    END MUTATION MAPPINGS

//    MÉTODOS PARA EVITAR DUPLICADOS

    //    Revisa atributos opcionales y si están presentes, los agregará al objeto y a la base de datos
    private ClientModel getClientModel(ClienteInput cliente, ClientModel client) {

        cliente.nombre().ifPresent(client::setNombre);
        cliente.correo().ifPresent(client::setCorreo);
        cliente.edad().ifPresent(client::setEdad);
        cliente.foto().ifPresent(client::setFoto);
        cliente.descripcion().ifPresent(client::setDescripcion);

        return clienteRepository.save(
                client
        );
    }

    private ProviderModel getProviderModel(ProveedorInput proveedor, ProviderModel provider) {
        proveedor.nombre().ifPresent(provider::setNombre);
        proveedor.correo().ifPresent(provider::setCorreo);
        proveedor.edad().ifPresent(provider::setEdad);
        proveedor.foto().ifPresent(provider::setFoto);
        proveedor.descripcion().ifPresent(provider::setDescripcion);
        proveedor.telefono().ifPresent(provider::setTelefono);
        proveedor.pagWeb().ifPresent(provider::setPagWeb);
        proveedor.contactoRedes().ifPresent(provider::setContactoRedes);

        return proveedorRepository.save(
                provider
        );
    }

    private TransportModel getTransportModel(TransporteInput transporte, TransportModel transport, ProviderModel
            provider) {
        transporte.tipo().ifPresent(transport::setTipo);
        transporte.capacidad().ifPresent(transport::setCapacidad);
        transporte.operador().ifPresent(transport::setOperador);
        transporte.precio().ifPresent(transport::setPrecio);
        transporte.calificacion().ifPresent(transport::setCalificacion);
        transporte.origen().ifPresent(transport::setOrigen);
        transporte.destino().ifPresent(transport::setDestino);
        transporte.fechaSalida().ifPresent(transport::setFechaSalida);
        transporte.horaSalida().ifPresent(transport::setHoraSalida);
        transporte.duracionEstimada().ifPresent(transport::setDuracionEstimada);
        transporte.foto().ifPresent(transport::setFoto);
        transporte.descripcion().ifPresent(transport::setDescripcion);
        transporte.latitud().ifPresent(transport::setLatitud);
        transporte.longitud().ifPresent(transport::setLongitud);
        transport.setProveedor(provider);

        return transporteRepository.save(
                transport
        );
    }

    private AlojamientoModel getAlojamientoModel(AlojamientoInput alojamiento, AlojamientoModel aloj, ProviderModel
            provider) {
        alojamiento.nombre().ifPresent(aloj::setNombre);
        alojamiento.calificacion().ifPresent(aloj::setCalificacion);
        alojamiento.ubicacion().ifPresent(aloj::setUbicacion);
        alojamiento.precioPorNoche().ifPresent(aloj::setPrecioPorNoche);
        alojamiento.descripcion().ifPresent(aloj::setDescripcion);
        alojamiento.foto().ifPresent(aloj::setFoto);
        alojamiento.latitud().ifPresent(aloj::setLatitud);
        alojamiento.longitud().ifPresent(aloj::setLongitud);
        aloj.setProveedor(provider);

        return alojamientoRepository.save(
                aloj
        );
    }

}
