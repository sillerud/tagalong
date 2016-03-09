package no.westerdals.westbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Event {
    private Date startDate;
    private Date endDate;
    private String location;
    private String[] tags;
    private String description;
}
