package no.tagalong.model;

import lombok.Data;

@Data
public class CsvTag {
    private final String parent;
    private final String name;
    private final String description;
}
