package nl.saxion.cds.housingassociation.controllers;

import nl.saxion.cds.housingassociation.models.Home;
import nl.saxion.cds.housingassociation.providers.HomeProvider;
import nl.saxion.cds.housingassociation.services.ClientService;
import nl.saxion.cds.housingassociation.services.HomeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;

@RestController
@RequestMapping("/homes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HomeController {
    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping
    public Collection<Home> getHomes() {
        return homeService.getHomes();
    }

    @GetMapping("top-maintenance")
    public HashMap<Long, Integer> getTopMaintenanceHomes() {
        return homeService.getTopMaintenanceHomes();
    }
}
