package nl.saxion.cds.housingassociation.services;

import nl.saxion.cds.housingassociation.models.Home;
import nl.saxion.cds.housingassociation.providers.HomeProvider;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class HomeService {
    private static HashMap<String, Home> homes = HomeProvider.homes;

    public Collection<Home> getHomes() {
        return homes.values();
    }
}
