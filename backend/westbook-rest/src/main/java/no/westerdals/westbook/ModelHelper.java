package no.westerdals.westbook;

import java.lang.reflect.Field;

public class ModelHelper {
    //TODO: Redo this
    public static <T>T mapObjects(T toUpdate, T updated, Class<T> cl) {
        try {
            for (Field field: cl.getFields()) {
                Object value = field.get(updated);
                if (value != null) {
                    field.set(toUpdate, value);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return toUpdate;
    }
}
