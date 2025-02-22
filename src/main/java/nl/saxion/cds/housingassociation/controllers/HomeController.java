package nl.saxion.cds.housingassociation.controllers;

import nl.saxion.cds.housingassociation.graph.BreadthFirstNode;
import nl.saxion.cds.housingassociation.graph.Graph;
import nl.saxion.cds.housingassociation.models.home.Home;
import nl.saxion.cds.housingassociation.models.home.TopMaintenanceHome;
import nl.saxion.cds.housingassociation.services.HomeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
    public List<TopMaintenanceHome> getTotalMaintenanceCosts() {
        return homeService.getTotalMaintenanceCosts();
    }

    @GetMapping("route-dijkstra-shortestpath")
    public Graph calculateShortestPathFromSource() {
        return homeService.calculateShortestPathFromSource();
    }

    @GetMapping("route-breadth-first-search")
    public Optional<BreadthFirstNode<Integer>> BreadthFirstSearch() {
        return homeService.BreadthFirstSearch();
    }
}
