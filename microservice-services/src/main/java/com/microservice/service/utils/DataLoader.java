package com.microservice.service.utils;//package com.microservice.service.utils;

import com.microservice.service.model.AlojamientoModel;
import com.microservice.service.model.ClientModel;
import com.microservice.service.model.ProviderModel;
import com.microservice.service.model.TransportModel;
import com.microservice.service.model.intermedias.Alojamiento_Persona;
import com.microservice.service.model.intermedias.Transporte_Persona;
import com.microservice.service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {
//
//    @Autowired
//    private ClienteRepository clienteRepository;
//
    @Autowired
    private ProveedorRepository proveedorRepository;
//
    @Autowired
    private AlojamientoRepository alojamientoRepository;
    @Autowired
    private TransporteRepository transporteRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private Transporte_PersonaRepository transporte_PersonaRepository;
    @Autowired
    private Alojamiento_PersonaRepository alojamiento_PersonaRepository;

    @Override
    public void run(String... args) throws Exception {
        cargarDatos();
    }
//
    public void cargarDatos() {
        ProviderModel proveedor = proveedorRepository.findByCorreo("1@123.com");
        ClientModel cliente = clienteRepository.findByCorreo("alejo@123.com");

        Random random = new Random();

        List<String> fotosAlojamiento = Arrays.asList(
                "https://storage.googleapis.com/cloud-project-javeriana/alojamientos/4x4_desierto_tunez.jpg",
                "https://storage.googleapis.com/cloud-project-javeriana/alojamientos/HHalemania.webp",
                "https://storage.googleapis.com/cloud-project-javeriana/alojamientos/playa.webp",
                "https://storage.googleapis.com/cloud-project-javeriana/alojamientos/turismo.webp"
        );

        List<AlojamientoModel> lista = Arrays.asList(
                AlojamientoModel.builder()
                        .nombre("Hotel Sol Caribe")
                        .foto(fotosAlojamiento.get(random.nextInt(fotosAlojamiento.size())))
                        .calificacion(4.5f)
                        .ubicacion("Cartagena, Colombia")
                        .precioPorNoche(200.0)
                        .proveedor(proveedor)
                        .latitud(10.3910f)
                        .longitud(-75.4794f)
                        .descripcion("Un hermoso hotel ubicado en el corazón histórico de Cartagena, perfecto para disfrutar de la cultura y playas caribeñas.")
                        .build(),

                AlojamientoModel.builder()
                        .nombre("Casa Blanca Resort")
                        .foto(fotosAlojamiento.get(random.nextInt(fotosAlojamiento.size())))
                        .calificacion(4.7f)
                        .ubicacion("Punta Cana, República Dominicana")
                        .precioPorNoche(250.0)
                        .proveedor(proveedor)
                        .latitud(18.5820f)
                        .longitud(-68.4043f)
                        .descripcion("Resort de lujo con acceso directo a las playas de arena blanca de Punta Cana, ideal para una experiencia tropical inolvidable.")
                        .build(),

                AlojamientoModel.builder()
                        .nombre("Eco Lodge")
                        .foto(fotosAlojamiento.get(random.nextInt(fotosAlojamiento.size())))
                        .calificacion(4.2f)
                        .ubicacion("San Andrés, Colombia")
                        .precioPorNoche(180.0)
                        .proveedor(proveedor)
                        .latitud(12.5847f)
                        .longitud(-81.7006f)
                        .descripcion("Alojamiento ecológico en la paradisíaca isla de San Andrés, perfecto para quienes buscan conectarse con la naturaleza.")
                        .build(),

                AlojamientoModel.builder()
                        .nombre("Cabañas del Bosque")
                        .foto(fotosAlojamiento.get(random.nextInt(fotosAlojamiento.size())))
                        .calificacion(4.8f)
                        .ubicacion("Monteverde, Costa Rica")
                        .precioPorNoche(120.0)
                        .proveedor(proveedor)
                        .latitud(10.3034f)
                        .longitud(-84.8255f)
                        .descripcion("Encantadoras cabañas rodeadas de la exuberante naturaleza del bosque de Monteverde, perfectas para los amantes de la tranquilidad.")
                        .build(),

                AlojamientoModel.builder()
                        .nombre("Penthouse Sunset")
                        .foto(fotosAlojamiento.get(random.nextInt(fotosAlojamiento.size())))
                        .calificacion(4.9f)
                        .ubicacion("Ciudad de México, México")
                        .precioPorNoche(300.0)
                        .proveedor(proveedor)
                        .latitud(19.4326f)
                        .longitud(-99.1332f)
                        .descripcion("Lujoso penthouse en el corazón de la Ciudad de México, con vistas espectaculares de la ciudad y acceso a sitios históricos.")
                        .build(),

                // Nuevos alojamientos
                AlojamientoModel.builder()
                        .nombre("Villa del Lago")
                        .foto(fotosAlojamiento.get(random.nextInt(fotosAlojamiento.size())))
                        .calificacion(4.6f)
                        .ubicacion("Bariloche, Argentina")
                        .precioPorNoche(270.0)
                        .proveedor(proveedor)
                        .latitud(-41.1335f)
                        .longitud(-71.3103f)
                        .descripcion("Una encantadora villa junto al lago, rodeada de montañas, perfecta para los amantes de la naturaleza y el senderismo.")
                        .build(),

                AlojamientoModel.builder()
                        .nombre("Safari Lodge")
                        .foto(fotosAlojamiento.get(random.nextInt(fotosAlojamiento.size())))
                        .calificacion(4.7f)
                        .ubicacion("Kruger, Sudáfrica")
                        .precioPorNoche(350.0)
                        .proveedor(proveedor)
                        .latitud(-23.9884f)
                        .longitud(31.5547f)
                        .descripcion("Lodge de lujo en medio del parque nacional Kruger, ideal para una experiencia de safari inolvidable.")
                        .build(),

                AlojamientoModel.builder()
                        .nombre("Bali Bliss Retreat")
                        .foto(fotosAlojamiento.get(random.nextInt(fotosAlojamiento.size())))
                        .calificacion(4.9f)
                        .ubicacion("Ubud, Indonesia")
                        .precioPorNoche(320.0)
                        .proveedor(proveedor)
                        .latitud(-8.5069f)
                        .longitud(115.2625f)
                        .descripcion("Retiro de lujo en Bali con vistas espectaculares de los campos de arroz y una atmósfera de total relajación.")
                        .build(),

                AlojamientoModel.builder()
                        .nombre("Desert Oasis")
                        .foto(fotosAlojamiento.get(random.nextInt(fotosAlojamiento.size())))
                        .calificacion(4.3f)
                        .ubicacion("Dubai, Emiratos Árabes Unidos")
                        .precioPorNoche(400.0)
                        .proveedor(proveedor)
                        .latitud(25.2769f)
                        .longitud(55.2962f)
                        .descripcion("Un oasis en el desierto con lujosas instalaciones y vistas impresionantes de las dunas.")
                        .build(),

                AlojamientoModel.builder()
                        .nombre("Alpine Chalet")
                        .foto(fotosAlojamiento.get(random.nextInt(fotosAlojamiento.size())))
                        .calificacion(4.8f)
                        .ubicacion("Zermatt, Suiza")
                        .precioPorNoche(500.0)
                        .proveedor(proveedor)
                        .latitud(46.0207f)
                        .longitud(7.7491f)
                        .descripcion("Chalet de lujo en los Alpes suizos, ideal para unas vacaciones de esquí o relajación.")
                        .build()
        );


        alojamientoRepository.saveAll(lista);

        String[] fotosTransporte = {
                "https://storage.googleapis.com/cloud-project-javeriana/transportes/Mueve-electric-buses.png",
                "https://storage.googleapis.com/cloud-project-javeriana/transportes/bus.jpg",
                "https://storage.googleapis.com/cloud-project-javeriana/transportes/buses-electricos-sitp-.jpg"
        };

        List<TransportModel> listaTransporte = Arrays.asList(
                TransportModel.builder()
                        .id("1")
                        .tipo("Bus")
                        .foto(fotosTransporte[random.nextInt(fotosTransporte.length)])
                        .capacidad(50)
                        .operador("Transporte Nacional")
                        .precio(120.0f)
                        .calificacion(4.5f)
                        .origen("Bogotá, Colombia")
                        .destino("Medellín, Colombia")
                        .fechaSalida("2023-12-01")
                        .horaSalida("08:00")
                        .duracionEstimada(420)
                        .descripcion("Viaje cómodo y seguro entre Bogotá y Medellín.")
                        .latitud(4.7110f)
                        .longitud(-74.0721f)
                        .proveedor(proveedor)
                        .build(),

                TransportModel.builder()
                        .id("2")
                        .tipo("Microbús")
                        .foto(fotosTransporte[random.nextInt(fotosTransporte.length)])
                        .capacidad(15)
                        .operador("EcoTrans")
                        .precio(60.0f)
                        .calificacion(4.2f)
                        .origen("Cali, Colombia")
                        .destino("Popayán, Colombia")
                        .fechaSalida("2023-12-02")
                        .horaSalida("10:30")
                        .duracionEstimada(180)
                        .descripcion("Servicio de transporte ecológico entre Cali y Popayán.")
                        .latitud(3.4516f)
                        .longitud(-76.5315f)
                        .proveedor(proveedor)
                        .build(),

                TransportModel.builder()
                        .id("3")
                        .tipo("Taxi")
                        .foto(fotosTransporte[random.nextInt(fotosTransporte.length)])
                        .capacidad(4)
                        .operador("Taxi Seguro")
                        .precio(20.0f)
                        .calificacion(4.7f)
                        .origen("Medellín, Colombia")
                        .destino("Aeropuerto José María Córdova")
                        .fechaSalida("2023-12-03")
                        .horaSalida("14:00")
                        .duracionEstimada(60)
                        .descripcion("Transporte rápido y seguro al aeropuerto.")
                        .latitud(6.2518f)
                        .longitud(-75.5636f)
                        .proveedor(proveedor)
                        .build(),

                TransportModel.builder()
                        .id("4")
                        .tipo("Tren")
                        .foto(fotosTransporte[random.nextInt(fotosTransporte.length)])
                        .capacidad(100)
                        .operador("Ferrocarriles de Colombia")
                        .precio(80.0f)
                        .calificacion(4.4f)
                        .origen("Bogotá, Colombia")
                        .destino("Zipaquirá, Colombia")
                        .fechaSalida("2023-12-04")
                        .horaSalida("09:00")
                        .duracionEstimada(90)
                        .descripcion("Viaje en tren desde Bogotá hacia la hermosa ciudad de Zipaquirá.")
                        .latitud(5.0221f)
                        .longitud(-74.0048f)
                        .proveedor(proveedor)
                        .build(),

                TransportModel.builder()
                        .id("5")
                        .tipo("Lancha")
                        .foto(fotosTransporte[random.nextInt(fotosTransporte.length)])
                        .capacidad(8)
                        .operador("Náutica Caribe")
                        .precio(150.0f)
                        .calificacion(4.8f)
                        .origen("Cartagena, Colombia")
                        .destino("Islas del Rosario")
                        .fechaSalida("2023-12-05")
                        .horaSalida("11:00")
                        .duracionEstimada(45)
                        .descripcion("Disfruta de un paseo en lancha por el Caribe hacia las Islas del Rosario.")
                        .latitud(10.3910f)
                        .longitud(-75.4794f)
                        .proveedor(proveedor)
                        .build()
        );

        transporteRepository.saveAll(listaTransporte);

        List<Transporte_Persona> listaTransportePersona = Arrays.asList(
                Transporte_Persona.builder()
                        .transporte(listaTransporte.get(0))
                        .cliente(cliente)
                        .numeroPlaca("ABC123")
                        .comentario("Excelente servicio, muy cómodo y puntual.")
                        .calificacion(4.5f)
                        .build(),

                Transporte_Persona.builder()
                        .transporte(listaTransporte.get(1))
                        .cliente(cliente)
                        .numeroPlaca("XYZ456")
                        .comentario("El microbús estaba limpio y el conductor fue muy amable.")
                        .calificacion(4.2f)
                        .build(),

                Transporte_Persona.builder()
                        .transporte(listaTransporte.get(2))
                        .cliente(cliente)
                        .numeroPlaca("DEF789")
                        .comentario("Rápido y seguro, llegué al aeropuerto sin problemas.")
                        .calificacion(4.7f)
                        .build(),

                Transporte_Persona.builder()
                        .transporte(listaTransporte.get(3))
                        .cliente(cliente)
                        .numeroPlaca("GHI012")
                        .comentario("El tren fue una experiencia única, lo recomiendo.")
                        .calificacion(4.4f)
                        .build(),

                Transporte_Persona.builder()
                        .transporte(listaTransporte.get(4))
                        .cliente(cliente)
                        .numeroPlaca("JKL345")
                        .comentario("El paseo en lancha fue increíble, paisajes hermosos.")
                        .calificacion(4.8f)
                        .build()
        );

        transporte_PersonaRepository.saveAll(listaTransportePersona);

        List<Alojamiento_Persona> listaAlojamientoPersona = Arrays.asList(
                Alojamiento_Persona.builder()
                        .alojamiento(lista.get(0))
                        .cliente(cliente)
                        .fechaCheckIn("2023-12-01")
                        .fechaCheckOut("2023-12-05")
                        .comentario("Excelente hotel, muy buena ubicación y servicio.")
                        .calificacion(4.5f)
                        .build(),

                Alojamiento_Persona.builder()
                        .alojamiento(lista.get(1))
                        .cliente(cliente)
                        .fechaCheckIn("2023-12-06")
                        .fechaCheckOut("2023-12-10")
                        .comentario("Resort de lujo, perfecto para unas vacaciones relajantes.")
                        .calificacion(4.7f)
                        .build(),

                Alojamiento_Persona.builder()
                        .alojamiento(lista.get(2))
                        .cliente(cliente)
                        .fechaCheckIn("2023-12-11")
                        .fechaCheckOut("2023-12-15")
                        .comentario("Un lugar mágico, ideal para desconectarse de la rutina.")
                        .calificacion(4.2f)
                        .build(),

                Alojamiento_Persona.builder()
                        .alojamiento(lista.get(3))
                        .cliente(cliente)
                        .fechaCheckIn("2023-12-16")
                        .fechaCheckOut("2023-12-20")
                        .comentario("Cabañas acogedoras en un entorno natural impresionante.")
                        .calificacion(4.8f)
                        .build(),

                Alojamiento_Persona.builder()
                        .alojamiento(lista.get(4))
                        .cliente(cliente)
                        .fechaCheckIn("2023-12-21")
                        .fechaCheckOut("2023-12-25")
                        .comentario("Vistas espectaculares desde")
                        .calificacion(4.9f)
                        .build()
        );

        alojamiento_PersonaRepository.saveAll(listaAlojamientoPersona);

        System.out.println("Cliente, proveedor y alojamiento guardados exitosamente.");

    }
}
//
