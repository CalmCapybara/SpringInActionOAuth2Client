package com.calmcapybara.springinactionclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Configuration
public class OAuthClientConfig {

    @Bean
    public ClientRegistration clientRegistration() {
        return ClientRegistration
                .withRegistrationId("spring-action-client-registration-id")
                .issuerUri("http://auth-server:9000")
                .tokenUri("http://auth-server:9000/oauth2/token")
                .clientId("spring-action-client")
                .clientSecret("secret")
                .clientName("spring-action-client-name")
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("ingredients.read")
                .build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(ClientRegistration clientRegistration) {
        return new InMemoryClientRegistrationRepository(clientRegistration);
    }

    @Bean
    public OAuth2AuthorizedClientService auth2AuthorizedClientService(ClientRegistrationRepository clientRegistrationRepository) {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }

    @Bean
    public AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientService auth2AuthorizedClientService) {

        OAuth2AuthorizedClientProvider authorizedClientProvider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .clientCredentials()
                        .build();

        AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager =
                new AuthorizedClientServiceOAuth2AuthorizedClientManager(clientRegistrationRepository, auth2AuthorizedClientService);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }
}
