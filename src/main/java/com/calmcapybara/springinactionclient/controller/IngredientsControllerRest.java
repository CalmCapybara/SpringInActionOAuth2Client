package com.calmcapybara.springinactionclient.controller;

import com.calmcapybara.springinactionclient.entity.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
public class IngredientsControllerRest {
    @Autowired
    private AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager;

    @GetMapping(value = "/ingredients/all")
    public List<Ingredient> getProducts() {
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId("spring-action-client-registration-id")
                .principal("Demo Service")
                .build();

        OAuth2AuthorizedClient authorizedClient = this.authorizedClientManager.authorize(authorizeRequest);

        OAuth2AccessToken accessToken = Objects.requireNonNull(authorizedClient).getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken.getTokenValue());
        HttpEntity request = new HttpEntity(headers);

        ParameterizedTypeReference<List<Ingredient>> responseType = new ParameterizedTypeReference<>() {};

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Ingredient>> response = restTemplate.exchange(
                "http://localhost:8090/ingredients/all",
                HttpMethod.GET,
                request,
                responseType
        );
        List<Ingredient> ingredientList = response.getBody();
        System.out.println(ingredientList);
        return ingredientList;
    }
}
