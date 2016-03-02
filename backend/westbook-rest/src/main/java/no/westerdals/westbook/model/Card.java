package no.westerdals.westbook.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Card {
    @Id
    private String id;
    private String userId;
    private String name;
    private String description;
    private int lastSeen;
    private String[] filter;
}
