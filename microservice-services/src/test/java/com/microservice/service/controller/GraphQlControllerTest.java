package com.microservice.service.controller;

import com.microservice.service.model.AlojamientoModel;
import com.microservice.service.repository.AlojamientoRepository;
import com.microservice.service.repository.Alojamiento_PersonaRepository;
import com.microservice.service.repository.Alojamiento_ProveedorRepository;
import com.microservice.service.repository.ClienteRepository;
import com.microservice.service.repository.ProveedorRepository;
import com.microservice.service.repository.TransporteRepository;
import com.microservice.service.repository.Transporte_PersonaRepository;
import com.microservice.service.repository.Transporte_ProveedorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@GraphQlTest(GraphQlController.class)
public class GraphQlControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private AlojamientoRepository alojamientoRepository;

    @MockBean
    private Alojamiento_PersonaRepository alojamiento_personaRepository;

    @MockBean
    private Alojamiento_ProveedorRepository alojamiento_proveedorRepository;

    @MockBean
    private ClienteRepository clienteRepository;

    @MockBean
    private ProveedorRepository proveedorRepository;

    @MockBean
    private TransporteRepository transporteRepository;

    @MockBean
    private Transporte_PersonaRepository transporte_personaRepository;

    @MockBean
    private Transporte_ProveedorRepository transporte_proveedorRepository;

    @Test
    void testAlojamientos() {
        AlojamientoModel alojamiento1 = new AlojamientoModel();
        alojamiento1.setId("1");
        alojamiento1.setNombre("Alojamiento 1");

        AlojamientoModel alojamiento2 = new AlojamientoModel();
        alojamiento2.setId("2");
        alojamiento2.setNombre("Alojamiento 2");

        when(alojamientoRepository.findAll()).thenReturn(Arrays.asList(alojamiento1, alojamiento2));

        List<String> expectedIds = Arrays.asList(alojamiento1.getId(), alojamiento2.getId());
        List<String> expectedNames = Arrays.asList(alojamiento1.getNombre(), alojamiento2.getNombre());

        graphQlTester.document("{ alojamientos { id nombre } }")
                .execute()
                .path("alojamientos")
                .entityList(AlojamientoModel.class)
                .hasSize(2)
                .satisfies(alojamientos -> {
                    List<String> actualIds = alojamientos.stream().map(AlojamientoModel::getId).collect(Collectors.toList());
                    List<String> actualNames = alojamientos.stream().map(AlojamientoModel::getNombre).collect(Collectors.toList());
                    assertThat(actualIds).containsExactlyInAnyOrderElementsOf(expectedIds);
                    assertThat(actualNames).containsExactlyInAnyOrderElementsOf(expectedNames);
                });
    }
}