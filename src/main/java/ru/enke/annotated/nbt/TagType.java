package ru.enke.annotated.nbt;

import org.jetbrains.annotations.Nullable;

import java.util.*;

public enum TagType {

    END,
    BYTE(Byte.class, byte.class, Boolean.class, boolean.class),
    SHORT(Short.class, short.class),
    INTEGER(Integer.class, int.class),
    LONG(Long.class, long.class),
    FLOAT(Float.class, float.class),
    DOUBLE(Double.class, double.class),
    BYTE_ARRAY(byte[].class),
    STRING(String.class),
    LIST(List.class),
    COMPOUND(Map.class),
    INTEGER_ARRAY(int[].class);

    private static final Map<Class<?>, TagType> typesByClasses = new HashMap<>();
    private final List<Class<?>> classes;

    TagType(final Class<?>... classes) {
        this.classes = Collections.unmodifiableList(Arrays.asList(classes));
    }

    static {
        for(final TagType type : TagType.values()) {
            for(final Class<?> typeClass : type.classes) {
                typesByClasses.put(typeClass, type);
            }
        }
    }

    @Nullable
    public static TagType getTypeByClass(Class<?> typeClass) {
        return typesByClasses.get(typeClass);
    }

}
