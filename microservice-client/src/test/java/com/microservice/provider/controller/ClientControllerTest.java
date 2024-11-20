package com.microservice.provider.controller;

import com.microservice.provider.entities.ClientModel;
import com.microservice.provider.service.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClientServiceImpl clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    public void testSaveStudent() throws Exception {
        ClientModel client = new ClientModel();
        client.setId("1");
        client.setNombre("Test Name");
        client.setCorreo("test@example.com");
        client.setEdad(25);
        client.setFoto("test.jpg");
        client.setDescripcion("Test Description");

        doNothing().when(clientService).save(client);

        mockMvc.perform(post("/api/client/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\",\"nombre\":\"Test Name\",\"correo\":\"test@example.com\",\"edad\":25,\"foto\":\"test.jpg\",\"descripcion\":\"Test Description\"}"))
                .andExpect(status().isCreated());
    }
}