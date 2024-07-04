package com.sofrecom.Gestion_des_zones.Security;

import com.sofrecom.zones.security.JwtAuthConverter;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class JwtAuthConverterTest {

    @Test
    void convert() {
        // Given
        Jwt jwt = mock(Jwt.class);
        JwtAuthConverter jwtAuthConverter = new JwtAuthConverter();

        // When
        AbstractAuthenticationToken authenticationToken = jwtAuthConverter.convert(jwt);

        // Then
        assertEquals(JwtAuthenticationToken.class, authenticationToken.getClass());
    }
}
