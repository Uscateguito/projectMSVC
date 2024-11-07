package com.microservice.provider.controller;

import com.microservice.provider.entities.ClientModel;
import com.microservice.provider.entities.inputs.UserDTO;
import com.microservice.provider.service.ClientServiceImpl;
import com.microservice.provider.service.IKeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
@RequestMapping("/api/client/auth")
public class AuthController {

    @Value("${keycloak.auth-server-url}")
    private String keycloakAuthServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;
//
    @Autowired
    private IKeycloakService keycloakService;

    @Autowired
    private ClientServiceImpl clientService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        RestTemplate restTemplate = new RestTemplate();

        // Endpoint de Token en Keycloak
        String url = keycloakAuthServerUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        // Crear el cuerpo de la solicitud
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Crear los parámetros de la solicitud en formato x-www-form-urlencoded
        String body = UriComponentsBuilder.newInstance()
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("grant_type", "password")
                .queryParam("username", username)
                .queryParam("password", password)
                .build()
                .toUriString().substring(1); // Remueve el '?' inicial

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        try {
            // Enviar solicitud de autenticación a Keycloak
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

            // Retornar el token JWT al frontend si la autenticación es exitosa
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) throws URISyntaxException {
        String response = keycloakService.createUser(userDTO);
        ClientModel client = ClientModel.builder()
                .nombre(userDTO.getFirstName())
                .correo(userDTO.getEmail())
                .build();

        clientService.save(client);
        return ResponseEntity.created(new URI("/keycloak/user/create")).body(response);
    }
}
