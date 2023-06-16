package com.autobots.automanager.models;

import com.autobots.automanager.entities.Adress;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdressSelect {

    public Adress selector(List<Adress> adresses, long id) {
        Adress selected = null;
        for (Adress adress : adresses) {
            if (adress.getId() == id) {
                selected = adress;
            }
        }
        return selected;
    }

}
