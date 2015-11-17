package no.westerdals.westbook.rest.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Pages
{
    @Id
    private final String id;
    private final String userId;
    private final String contactInfo;
    private final Link[] links;
}
