package com.autobots.automanager.models;

import com.autobots.automanager.entities.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentSelect {

    public Document selector(List<Document> documents, long id) {
        Document selected = null;
        for (Document document : documents) {
            if (document.getId() == id) {
                selected = document;
            }
        }
        return selected;
    }
}
