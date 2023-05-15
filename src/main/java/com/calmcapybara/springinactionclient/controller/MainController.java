//package com.calmcapybara.springinactionclient.controller;
//
//import com.calmcapybara.springinactionclient.entity.Ingredient;
//import com.calmcapybara.springinactionclient.service.IngredientService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class MainController {
//
//    IngredientService ingredientService;
//
//    public MainController(IngredientService ingredientService) {
//        this.ingredientService = ingredientService;
//    }
//
//    @GetMapping("/ingredients/show")
//    public String showIngredients(Model model) {
//
//        Iterable<Ingredient> allIngredients = ingredientService.getAllIngredients();
//        System.out.println(allIngredients);
//        model.addAttribute("ingredients", allIngredients);
//        return "show_ingredients";
//    }
//}
