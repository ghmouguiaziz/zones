package com.sofrecom.Gestion_des_zones.Security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

/*import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
@Configuration
@SpringBootTest
class SecurityConfigTest {

    @MockBean
    private JwtAuthConverter jwtAuthConverter;

    @Test
    void securityFilterChain() throws Exception {
        // Mocking the JwtDecoder
        JwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri("http://localhost:8080/realms/SpringBootDemo/protocol/openid-connect/certs").build();

        // Mocking the HttpSecurity
        HttpSecurity httpSecurity = mock(HttpSecurity.class);

        // Creating an instance of SecurityConfig
        SecurityConfig securityConfig = new SecurityConfig(jwtAuthConverter);

        // Testing the security filter chain
        SecurityFilterChain securityFilterChain = securityConfig.securityFilterChain(httpSecurity);

        // Add your assertions here
        Assertions.assertNotNull(securityFilterChain, "Security filter chain should not be null");
        Assertions.assertEquals(2, securityFilterChain.getFilters().size(), "Expected number of filters");

        // Add more assertions as needed
    }
}
*/
