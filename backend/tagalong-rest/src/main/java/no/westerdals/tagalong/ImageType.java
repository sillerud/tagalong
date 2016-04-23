package no.westerdals.tagalong;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageType {
    HEADER_IMAGE(1920, 1080),
    PROFILE_IMAGE(1000, 1000),
    CARD_IMAGE(600, 600),
    OTHER(1920, 1080);
    private final int maxWidth;
    private final int maxHeight;
}
