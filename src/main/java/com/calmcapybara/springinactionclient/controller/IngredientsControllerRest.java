package com.calmcapybara.springinactionclient.controller;

import com.calmcapybara.springinactionclient.entity.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@RestController
public class IngredientsControllerRest {
    private final WebClient webClient;

    public IngredientsControllerRest(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping(value = "/ingredients/all")
    public List<Ingredient> getProducts() {
        return this.webClient
                .get()
                .uri("http://127.0.0.1:8090/ingredients/all")
                .attributes(clientRegistrationId("spring-action-client-client-credentials"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Ingredient>>() {})
                .block();
    }
}
