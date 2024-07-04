package com.sofrecom.zones.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter =
            new JwtGrantedAuthoritiesConverter();


    private static final String PRINCIPLEATTRIBUTE = "preferred_username";

    private static final String RESOURCEID = "MTC";

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream.concat(
                safeConvert(jwtGrantedAuthoritiesConverter.convert(jwt)).stream(),
                extractResourceRoles(jwt).stream()
        ).collect(Collectors.toSet());

        return new JwtAuthenticationToken(
                jwt,
                authorities,
                getPrincipleClaimName(jwt)
        );
    }

    private Collection<GrantedAuthority> safeConvert(Collection<GrantedAuthority> authorities) {
        return authorities != null ? authorities : Collections.emptyList();
    }

    private String getPrincipleClaimName(Jwt jwt) {
        return jwt.getClaim(PRINCIPLEATTRIBUTE);
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map<String, Object> resourceAccess;
        Map<String, Object> resource;
        Collection<String> resourceRoles;

        if (jwt.getClaim("resource_access") == null) {
            return Set.of();
        }
        resourceAccess = jwt.getClaim("resource_access");

        if (resourceAccess.get(RESOURCEID) == null) {
            return Set.of();
        }
        resource = (Map<String, Object>) resourceAccess.get(RESOURCEID);

        resourceRoles = (Collection<String>) resource.get("roles");
        if (resourceRoles == null) {
            return Set.of();
        }

        return resourceRoles
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }
}
