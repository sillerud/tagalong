package no.westerdals.westbook.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Link
{
    @Id
    private final String id;
    private final String description;
    private final String url;
}
