package no.westerdals.westbook.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Tag
{
    private boolean displayed;
    private String name;
}