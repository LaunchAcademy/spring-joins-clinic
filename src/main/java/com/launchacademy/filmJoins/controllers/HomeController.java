package com.launchacademy.filmJoins.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = {"/studios", "/studios/{id}", "/films", "/films/new", "/films/{id}", "/actors/new"})
    public String forward() {
        return "forward:/";
    }
}
