package com.autobots.automanager.models;

import com.autobots.automanager.entities.Telephone;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TelephoneSelect {

    public Telephone selector(List<Telephone> telephones, long id) {
        Telephone selected = null;
        for (Telephone telephone : telephones) {
            if (telephone.getId() == id) {
                selected = telephone;
            }
        }
        return selected;
    }
}
