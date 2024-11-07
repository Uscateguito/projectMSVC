package com.microservice.service.utils;//package com.microservice.service.utils;
//
//import com.microservice.service.model.AlojamientoModel;
//import com.microservice.service.model.ClienteModel;
//import com.microservice.service.model.ProveedorModel;
//import com.microservice.service.repository.AlojamientoRepository;
//import com.microservice.service.repository.ClienteRepository;
//import com.microservice.service.repository.ProveedorRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataLoader {
//
//    @Autowired
//    private ClienteRepository clienteRepository;
//
//    @Autowired
//    private ProveedorRepository proveedorRepository;
//
//    @Autowired
//    private AlojamientoRepository alojamientoRepository;
//
//    public void cargarDatos() {
//        // Crear un objeto ClienteModel
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
//
//        // Guardar alojamiento en la base de datos
//        AlojamientoModel alojamientoGuardado = alojamientoRepository.save(alojamiento);
//        proveedorGuardado.getAlojamientos().add(alojamientoGuardado);
//        clienteGuardado.getAlojamientos().add(alojamientoGuardado);
//        clienteRepository.save(clienteGuardado);
//        proveedorRepository.save(proveedorGuardado);
//
//        // Imprimir la confirmación de guardado
//        System.out.println("Cliente, proveedor y alojamiento guardados exitosamente.");
//    }
//}
//
