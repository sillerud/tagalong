package no.westerdals.westbook.model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RequestResponse
{
    public RequestResponse(String response)
    {
        this.response = response;
    }

    private String response;
}
