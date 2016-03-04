package no.westerdals.westbook.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostTag
{
    private boolean displayed;
    private String tagId;
}