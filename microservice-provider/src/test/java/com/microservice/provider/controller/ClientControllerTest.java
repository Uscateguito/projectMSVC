package com.microservice.provider.controller;

import com.microservice.provider.entities.ProviderModel;
import com.microservice.provider.service.ClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientServiceImpl clientService;

    @Test
    @WithMockUser
    public void testSaveStudent() throws Exception {
        ProviderModel provider = new ProviderModel();
        provider.setId("1");
        provider.setNombre("Test Provider");
        provider.setCorreo("test@provider.com");
        provider.setEdad(30);
        provider.setFoto("test.jpg");
        provider.setDescripcion("Test Description");
        provider.setTelefono("123456789");
        provider.setPagWeb("http://test.com");
        provider.setContactoRedes("test_social");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/provider/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": \"1\", \"nombre\": \"Test Provider\", \"correo\": \"test@provider.com\", \"edad\": 30, \"foto\": \"test.jpg\", \"descripcion\": \"Test Description\", \"telefono\": \"123456789\", \"pagWeb\": \"http://test.com\", \"contactoRedes\": \"test_social\" }"))
                .andExpect(status().isCreated());
    }
}