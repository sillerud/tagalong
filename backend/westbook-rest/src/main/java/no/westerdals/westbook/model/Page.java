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
public class Page
{
    @Id
    private final String id;
    private final String name;
    private final String userId;
    private final String contactInfo;
    private final Link[] links;
}
