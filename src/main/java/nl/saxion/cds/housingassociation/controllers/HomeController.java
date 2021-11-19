package nl.saxion.cds.housingassociation.controllers;

import nl.saxion.cds.housingassociation.models.Home;
import nl.saxion.cds.housingassociation.providers.HomeProvider;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/homes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HomeController {
    public static HashMap<String, Home> homes = HomeProvider.homes;

    @GetMapping
    public HashMap<String, Home> getHomes() {
        return homes;
    }
}
