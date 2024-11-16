package com.microservice.service.utils;//package com.microservice.service.utils;

import com.microservice.service.model.AlojamientoModel;
import com.microservice.service.model.ProviderModel;
import com.microservice.service.model.TransportModel;
import com.microservice.service.repository.AlojamientoRepository;
import com.microservice.service.repository.ClienteRepository;
import com.microservice.service.repository.ProveedorRepository;
import com.microservice.service.repository.TransporteRepository;
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

    @Override
    public void run(String... args) throws Exception {
        cargarDatos();
    }
//
    public void cargarDatos() {
        // Crear un objeto ClienteModel
//        ClienteModel cliente = new ClienteModel();
//        cliente.setNombre("Juan Pérez");
//        cliente.setCorreo("juan.perez@ejemplo.com");
//        cliente.setEdad(30);
//        cliente.setFoto("url_foto_cliente.jpg");
//        cliente.setDescripcion("Cliente habitual que viaja por trabajo.");
//
//        // Guardar cliente en la base de datos
//        ClienteModel clienteGuardado = clienteRepository.save(cliente);
//
//        // Crear un objeto ProveedorModel
//        ProveedorModel proveedor = new ProveedorModel();
//        proveedor.setNombre("Hotel ABC");
//        proveedor.setEdad(15); // Años de operación del proveedor
//        proveedor.setFoto("url_foto_hotel.jpg");
//        proveedor.setDescripcion("Hotel de 4 estrellas en el centro de la ciudad.");
//        proveedor.setTelefono("123456789");
//        proveedor.setPagWeb("http://hotelabc.com");
//        proveedor.setContactoRedes("@hotel_abc");
//
//        // Guardar proveedor en la base de datos
//        ProveedorModel proveedorGuardado = proveedorRepository.save(proveedor);
//
//        // Crear un objeto AlojamientoModel
//        AlojamientoModel alojamiento = new AlojamientoModel();
//        alojamiento.setNombre("Habitación Deluxe");
//        alojamiento.setUbicacion("Centro de la ciudad");
//        alojamiento.setPrecioPorNoche(120.0);
//        alojamiento.setFechaCheckIn("2024-11-01");
//        alojamiento.setFechaCheckOut("2024-11-05");
//        alojamiento.setProveedor(proveedorGuardado);
//        alojamiento.setCliente(clienteGuardado);

        // Guardar alojamiento en la base de datos
//        AlojamientoModel alojamientoGuardado = alojamientoRepository.save(alojamiento);
//        proveedorGuardado.getAlojamientos().add(alojamientoGuardado);
//        clienteGuardado.getAlojamientos().add(alojamientoGuardado);
//        clienteRepository.save(clienteGuardado);
//        proveedorRepository.save(proveedorGuardado);

        ProviderModel proveedor = proveedorRepository.findByCorreo("1@123.com");

//        List<AlojamientoModel> lista = Arrays.asList(
//                AlojamientoModel.builder()
//                        .nombre("Hotel Sol Caribe")
//                        .foto("https://example.com/foto1.jpg")
//                        .calificacion(4.5f)
//                        .ubicacion("Cartagena, Colombia")
//                        .precioPorNoche(200.0)
//                        .proveedor(proveedor)
//                        .latitud(10.3910f)
//                        .longitud(-75.4794f)
//                        .descripcion("Un hermoso hotel ubicado en el corazón histórico de Cartagena, perfecto para disfrutar de la cultura y playas caribeñas.")
//                        .build(),
//
//                AlojamientoModel.builder()
//                        .nombre("Casa Blanca Resort")
//                        .foto("https://example.com/foto2.jpg")
//                        .calificacion(4.7f)
//                        .ubicacion("Punta Cana, República Dominicana")
//                        .precioPorNoche(250.0)
//                        .proveedor(proveedor)
//                        .latitud(18.5820f)
//                        .longitud(-68.4043f)
//                        .descripcion("Resort de lujo con acceso directo a las playas de arena blanca de Punta Cana, ideal para una experiencia tropical inolvidable.")
//                        .build(),
//
//                AlojamientoModel.builder()
//                        .nombre("Eco Lodge")
//                        .foto("https://example.com/foto3.jpg")
//                        .calificacion(4.2f)
//                        .ubicacion("San Andrés, Colombia")
//                        .precioPorNoche(180.0)
//                        .proveedor(proveedor)
//                        .latitud(12.5847f)
//                        .longitud(-81.7006f)
//                        .descripcion("Alojamiento ecológico en la paradisíaca isla de San Andrés, perfecto para quienes buscan conectarse con la naturaleza.")
//                        .build(),
//
//                AlojamientoModel.builder()
//                        .nombre("Cabañas del Bosque")
//                        .foto("https://example.com/foto4.jpg")
//                        .calificacion(4.8f)
//                        .ubicacion("Monteverde, Costa Rica")
//                        .precioPorNoche(120.0)
//                        .proveedor(proveedor)
//                        .latitud(10.3034f)
//                        .longitud(-84.8255f)
//                        .descripcion("Encantadoras cabañas rodeadas de la exuberante naturaleza del bosque de Monteverde, perfectas para los amantes de la tranquilidad.")
//                        .build(),
//
//                AlojamientoModel.builder()
//                        .nombre("Penthouse Sunset")
//                        .foto("https://example.com/foto5.jpg")
//                        .calificacion(4.9f)
//                        .ubicacion("Ciudad de México, México")
//                        .precioPorNoche(300.0)
//                        .proveedor(proveedor)
//                        .latitud(19.4326f)
//                        .longitud(-99.1332f)
//                        .descripcion("Lujoso penthouse en el corazón de la Ciudad de México, con vistas espectaculares de la ciudad y acceso a sitios históricos.")
//                        .build(),
//
//                AlojamientoModel.builder()
//                        .nombre("Beachfront Paradise")
//                        .foto("https://example.com/foto6.jpg")
//                        .calificacion(4.6f)
//                        .ubicacion("Búzios, Brasil")
//                        .precioPorNoche(280.0)
//                        .proveedor(proveedor)
//                        .latitud(-22.7469f)
//                        .longitud(-41.8814f)
//                        .descripcion("Un paraíso frente al mar en Búzios, ideal para relajarse en playas paradisíacas y disfrutar de la vida nocturna brasileña.")
//                        .build(),
//
//                AlojamientoModel.builder()
//                        .nombre("Mountain View Lodge")
//                        .foto("https://example.com/foto7.jpg")
//                        .calificacion(4.7f)
//                        .ubicacion("Mendoza, Argentina")
//                        .precioPorNoche(220.0)
//                        .proveedor(proveedor)
//                        .latitud(-32.8908f)
//                        .longitud(-68.8272f)
//                        .descripcion("Lodge con vistas impresionantes a las montañas de Mendoza, perfecto para amantes del vino y el turismo de aventura.")
//                        .build(),
//
//                AlojamientoModel.builder()
//                        .nombre("Lakefront Retreat")
//                        .foto("https://example.com/foto8.jpg")
//                        .calificacion(4.8f)
//                        .ubicacion("Pucón, Chile")
//                        .precioPorNoche(190.0)
//                        .proveedor(proveedor)
//                        .latitud(-39.2824f)
//                        .longitud(-71.9535f)
//                        .descripcion("Retiro frente al lago en Pucón, rodeado de paisajes volcánicos, ideal para actividades al aire libre como senderismo y kayak.")
//                        .build(),
//
//                AlojamientoModel.builder()
//                        .nombre("Jungle Haven")
//                        .foto("https://example.com/foto9.jpg")
//                        .calificacion(4.5f)
//                        .ubicacion("Iquitos, Perú")
//                        .precioPorNoche(160.0)
//                        .proveedor(proveedor)
//                        .latitud(-3.7437f)
//                        .longitud(-73.2516f)
//                        .descripcion("Alojamiento en plena selva amazónica de Iquitos, ofreciendo una experiencia auténtica y conexión con la naturaleza.")
//                        .build(),
//
//                AlojamientoModel.builder()
//                        .nombre("Historic Downtown Hotel")
//                        .foto("https://example.com/foto10.jpg")
//                        .calificacion(4.4f)
//                        .ubicacion("Quito, Ecuador")
//                        .precioPorNoche(210.0)
//                        .proveedor(proveedor)
//                        .latitud(-0.1807f)
//                        .longitud(-78.4678f)
//                        .descripcion("Hotel histórico en el centro de Quito, ubicado cerca de sitios patrimoniales y rodeado de la cultura local.")
//                        .build()
//        );


//        alojamientoRepository.saveAll(lista);

        String[] fotos = {
                "https://storage.googleapis.com/cloud-project-javeriana/transportes/Mueve-electric-buses.png",
                "https://storage.googleapis.com/cloud-project-javeriana/transportes/bus.jpg",
                "https://storage.googleapis.com/cloud-project-javeriana/transportes/buses-electricos-sitp-.jpg"
        };

        Random random = new Random();

        List<TransportModel> lista = Arrays.asList(
                TransportModel.builder()
                        .id("1")
                        .tipo("Bus")
                        .foto(fotos[random.nextInt(fotos.length)])
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
                        .build(),

                TransportModel.builder()
                        .id("2")
                        .tipo("Microbús")
                        .foto(fotos[random.nextInt(fotos.length)])
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
                        .build(),

                TransportModel.builder()
                        .id("3")
                        .tipo("Taxi")
                        .foto(fotos[random.nextInt(fotos.length)])
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
                        .build(),

                TransportModel.builder()
                        .id("4")
                        .tipo("Tren")
                        .foto(fotos[random.nextInt(fotos.length)])
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
                        .build(),

                TransportModel.builder()
                        .id("5")
                        .tipo("Lancha")
                        .foto(fotos[random.nextInt(fotos.length)])
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
                        .build()
        );

        transporteRepository.saveAll(lista);

        System.out.println("Cliente, proveedor y alojamiento guardados exitosamente.");

    }
}
//
