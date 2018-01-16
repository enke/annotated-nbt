package ru.enke.annotated.nbt.tag;

public class Tag<T> {

    public enum Type {
        END,
        BYTE,
        SHORT,
        INTEGER,
        LONG,
        FLOAT,
        DOUBLE,
        BYTE_ARRAY,
        STRING,
        LIST,
        COMPOUND,
        INTEGER_ARRAY
    }

    private final String name;
    private final T value;
    private final Type type;

    Tag(final String name, final T value, final Type type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

}
