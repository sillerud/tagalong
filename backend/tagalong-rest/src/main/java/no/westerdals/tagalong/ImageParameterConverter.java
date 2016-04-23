package no.westerdals.tagalong;

import java.beans.PropertyEditorSupport;

public class ImageParameterConverter extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        for (ImageType type : ImageType.values()) {
            if (type.name().equals(text))
                setValue(text);
        }
    }
}
