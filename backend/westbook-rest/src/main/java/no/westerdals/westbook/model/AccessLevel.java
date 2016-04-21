package no.westerdals.westbook.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AccessLevel {
    READ(0),
    EDIT(1),
    DELETE(2),
    ALL(10);
    public final int LEVEL;
}
