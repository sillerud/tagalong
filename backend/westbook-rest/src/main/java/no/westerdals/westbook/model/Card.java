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
    private String lastPostSeen;
    private String customBackgroundImageId;
    private String[] filter;
}
