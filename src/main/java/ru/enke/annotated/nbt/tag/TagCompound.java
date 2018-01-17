package ru.enke.annotated.nbt.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.enke.annotated.nbt.tag.Tag.Type.*;

public class TagCompound extends Tag<Map<String, Tag<?>>> {

    private final Map<String, Tag<?>> tags;

    public TagCompound() {
        this("");
    }

    public TagCompound(final String name) {
        this(name, new HashMap<>());
    }

    public TagCompound(final Tag<Map<String, Tag<?>>> tag) {
        this(tag.getName(), tag.getValue());
    }

    public TagCompound(final String name, final Map<String, Tag<?>> value) {
        super(name, value, COMPOUND);
        this.tags = getValue();
    }

    public int getSize() {
        return getValue().size();
    }

    private <T> T getValue(final String name, final Tag.Type expectedType, final Class<T> cls) {
        final Tag<?> tag = tags.get(name);

        if(tag == null) {
            throw new IllegalStateException("Tag with name " + name + " not found");
        }

        final Tag.Type type = tag.getType();

        if(expectedType != type) {
            throw new IllegalStateException("Tag type not matches. Expected: " + expectedType + " and actual: " + type);
        }

        return cls.cast(tag.getValue());
    }

    @SuppressWarnings("unchecked")
    private List<Tag<?>> getList(final String name, final Type expectedType) {
        final List<Tag<?>> list = getValue(name, LIST, List.class);

        if(!list.isEmpty()) {
            final Tag.Type type = list.get(0).getType();

            if(type != expectedType) {
                throw new IllegalStateException("List tag type not matches. Expected: " + expectedType + " and actual: " + type);
            }
        }

        return list;
    }

    private List<?> getListValue(final String name, final Type expectedType) {
        return getList(name, expectedType).stream()
                .map(Tag::getValue)
                .collect(Collectors.toList());
    }

    public byte getByte(final String name) {
        return getValue(name, BYTE, Byte.class);
    }

    public double getDouble(final String name) {
        return getValue(name, DOUBLE, Double.class);
    }

    public double getFloat(final String name) {
        return getValue(name, FLOAT, Float.class);
    }

    public int getInt(final String name) {
        return getValue(name, INTEGER, Integer.class);
    }

    public long getLong(final String name) {
        return getValue(name, LONG, Long.class);
    }

    public int getShort(final String name) {
        return getValue(name, SHORT, Short.class);
    }

    public String getString(final String name) {
        return getValue(name, STRING, String.class);
    }

    public byte[] getByteArray(final String name) {
        return getValue(name, BYTE_ARRAY, byte[].class);
    }

    public int[] getIntArray(final String name) {
        return getValue(name, INTEGER_ARRAY, int[].class);
    }

    @SuppressWarnings("unchecked")
    public TagCompound getCompound(final String name) {
        return new TagCompound(name, getValue(name, COMPOUND, Map.class));
    }

    @SuppressWarnings("unchecked")
    public List<Byte> getByteList(final String name) {
        return (List<Byte>) getListValue(name, BYTE);
    }

    @SuppressWarnings("unchecked")
    public List<Double> getDoubleList(final String name) {
        return (List<Double>) getListValue(name, DOUBLE);
    }

    @SuppressWarnings("unchecked")
    public List<Float> getFloatList(final String name) {
        return (List<Float>) getListValue(name, FLOAT);
    }

    @SuppressWarnings("unchecked")
    public List<Integer> getIntList(final String name) {
        return (List<Integer>) getListValue(name, INTEGER);
    }

    @SuppressWarnings("unchecked")
    public List<Long> getLongList(final String name) {
        return (List<Long>) getListValue(name, LONG);
    }

    @SuppressWarnings("unchecked")
    public List<String> getStringList(final String name) {
        return (List<String>) getListValue(name, STRING);
    }

    @SuppressWarnings("unchecked")
    public List<byte[]> getByteArrayList(final String name) {
        return (List<byte[]>) getListValue(name, BYTE_ARRAY);
    }

    @SuppressWarnings("unchecked")
    public List<int[]> getIntArrayList(final String name) {
        return (List<int[]>) getListValue(name, INTEGER_ARRAY);
    }

    @SuppressWarnings("unchecked")
    public List<TagCompound> getCompoundList(final String name) {
        return getList(name, COMPOUND).stream()
                .map(t -> new TagCompound((Tag<Map<String, Tag<?>>>) t))
                .collect(Collectors.toList());
    }

}