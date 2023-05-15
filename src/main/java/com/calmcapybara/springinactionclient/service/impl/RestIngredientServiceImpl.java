//package com.calmcapybara.springinactionclient.service.impl;
//
//import com.calmcapybara.springinactionclient.entity.Ingredient;
//import com.calmcapybara.springinactionclient.service.IngredientService;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//
//@Service
//public class RestIngredientServiceImpl implements IngredientService {
//    private final RestTemplate restTemplate;
//
//    public RestIngredientServiceImpl(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    @Override
//    public Iterable<Ingredient> getAllIngredients() {
//        return Arrays.asList(restTemplate.getForObject("http://localhost:8080/ingredients/all", Ingredient[].class));
//    }
//}
