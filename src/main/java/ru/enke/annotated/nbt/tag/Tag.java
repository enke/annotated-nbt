package ru.enke.annotated.nbt.tag;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

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

    public T getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(final @Nullable Object o) {
        if(this == o) {
            return true;
        }

        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        final Tag<?> tag = (Tag<?>) o;

        if(!name.equals(tag.name)) {
            return false;
        }

        if(type != tag.type) {
            return false;
        }

        switch(type) {
            case BYTE_ARRAY:
            case INTEGER_ARRAY:
                return Objects.deepEquals(value, tag.value);
            default:
                return value.equals(tag.value);
        }
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + type.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", type=" + type +
                '}';
    }
}
