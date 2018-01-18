package ru.enke.annotated.nbt.util;

import ru.enke.annotated.nbt.annotation.NBT;
import ru.enke.annotated.nbt.exception.TagCodecException;

import java.lang.reflect.Field;
import java.util.*;

public class ReflectionUtil {

    public static Map<String, Field> getTagsFields(final Class<?> cls) {
        final Map<String, Field> fields = new HashMap<>();

        for(final Field field : getFields(new ArrayList<>(), cls)) {
            final NBT annotation = field.getDeclaredAnnotation(NBT.class);

            if(annotation == null) {
                continue;
            }

            field.setAccessible(true);

            final String value = annotation.value();
            final String tagName = value.isEmpty() ? field.getName() : value;

            fields.put(tagName, field);
        }

        return fields;
    }

    private static List<Field> getFields(final List<Field> fields, final Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if(type.getSuperclass() != null) {
            getFields(fields, type.getSuperclass());
        }

        return fields;
    }

    public static <T> T createObject(final Class<T> cls) throws TagCodecException {
        try {
            return cls.getDeclaredConstructor().newInstance();
        } catch(final Exception e) {
            throw new TagCodecException("Failed to create object from " + cls + " constructor", e);
        }
    }

    public static void setFieldValue(final Field field, final Object object, final Object value) throws TagCodecException {
        try {
            field.set(object, value);
        } catch(final Exception e) {
            throw new TagCodecException("Failed to set field " + field.getName() + " value", e);
        }
    }

    public static Object getFieldValue(final Field field, final Object object) throws TagCodecException {
        try {
            return field.get(object);
        } catch(final Exception e) {
            throw new TagCodecException("Failed to get field " + field.getName() + " value", e);
        }
    }

}
