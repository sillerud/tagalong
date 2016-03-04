package no.westerdals.westbook.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SearchResult<T> {
    String type;
    T data;
}
