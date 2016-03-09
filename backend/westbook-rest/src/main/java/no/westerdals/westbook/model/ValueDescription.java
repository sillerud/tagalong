package no.westerdals.westbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ValueDescription implements Serializable {
    private String description;
    private String value;
    private static final long serialVersionUID = 9068711302098927061L;
}
