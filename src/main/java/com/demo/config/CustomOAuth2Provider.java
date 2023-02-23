package com.demo.config;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

public enum CustomOAuth2Provider {

    KEYCLOAK {
        public ClientRegistration.Builder getBuilder(String registrationId) {
            ClientRegistration.Builder builder = this.getBuilder(registrationId, ClientAuthenticationMethod.BASIC, "{baseUrl}/{action}/oauth2/code/{registrationId}");
            builder.authorizationUri("http://localhost:8080/auth/realms/SpringBootKeycloak/protocol/openid-connect/auth");
            builder.tokenUri("http://localhost:8080/auth/realms/SpringBootKeycloak/protocol/openid-connect/token");
            builder.jwkSetUri("http://localhost:8080/auth/realms/SpringBootKeycloak/protocol/openid-connect/certs");
            builder.scope(new String[]{"openid"});
            return builder;
        }
    };

    private static final String DEFAULT_REDIRECT_URL = "{baseUrl}/{action}/oauth2/code/{registrationId}";

    private CustomOAuth2Provider() {
    }

    protected final ClientRegistration.Builder getBuilder(String registrationId, ClientAuthenticationMethod method, String redirectUri) {
        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
        builder.clientAuthenticationMethod(method);
        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
        builder.redirectUri(redirectUri);
        return builder;
    }

    public abstract ClientRegistration.Builder getBuilder(String var1);
}
