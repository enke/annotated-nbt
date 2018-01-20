package ru.enke.annotated.nbt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.enke.annotated.nbt.TagType.*;

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

    public boolean containsTag(final String name) {
        return tags.containsKey(name);
    }

    private void setValue(final Tag<?> tag) {
        final String name = tag.getName();

        if(tags.containsKey(name)) {
            throw new IllegalStateException("Tag with name " + name + " already exists");
        }

        tags.put(name, tag);
    }

    private <T> T getValue(final String name, final TagType expectedType, final Class<T> cls) {
        final Tag<?> tag = tags.get(name);

        if(tag == null) {
            throw new IllegalStateException("Tag with name " + name + " not found");
        }

        final TagType type = tag.getType();

        if(expectedType != type) {
            throw new IllegalStateException("Tag type not matches. Expected: " + expectedType + " and actual: " + type);
        }

        return cls.cast(tag.getValue());
    }

    @SuppressWarnings("unchecked")
    private List<Tag<?>> getList(final String name, final TagType expectedType) {
        final List<Tag<?>> list = getValue(name, LIST, List.class);

        if(!list.isEmpty()) {
            final TagType type = list.get(0).getType();

            if(type != expectedType) {
                throw new IllegalStateException("List tag type not matches. Expected: " + expectedType + " and actual: " + type);
            }
        }

        return list;
    }

    private List<?> getListValue(final String name, final TagType expectedType) {
        return getList(name, expectedType).stream()
                .map(Tag::getValue)
                .collect(Collectors.toList());
    }

    public void setBoolean(final String name, final boolean value) {
        setByte(name, (byte) (value ? 1 : 0));
    }

    public void setByte(final String name, final byte value) {
        setValue(TagFactory.createByteTag(name, value));
    }

    public void setShort(final String name, final short value) {
        setValue(TagFactory.createShortTag(name, value));
    }

    public void setInt(final String name, final int value) {
        setValue(TagFactory.createIntTag(name, value));
    }

    public void setDouble(final String name, final double value) {
        setValue(TagFactory.createDoubleTag(name, value));
    }

    public void setFloat(final String name, final float value) {
        setValue(TagFactory.createFloatTag(name, value));
    }

    public void setLong(final String name, final long value) {
        setValue(TagFactory.createLongTag(name, value));
    }

    public void setString(final String name, final String value) {
        setValue(TagFactory.createStringTag(name, value));
    }

    public void setByteArray(final String name, final byte[] value) {
        setValue(TagFactory.createByteArrayTag(name, value));
    }

    public void setIntArray(final String name, final int[] value) {
        setValue(TagFactory.createIntArrayTag(name, value));
    }

    public void setBooleanList(final String name, final List<Boolean> list) {
        final List<Tag<?>> tagList = list.stream()
                .map(b -> TagFactory.createByteTag((byte) (b ? 1 : 0)))
                .collect(Collectors.toList());

        setValue(TagFactory.createListTag(name, tagList));
    }

    public void setByteList(final String name, final List<Byte> list) {
        final List<Tag<?>> tagList = list.stream()
                .map(TagFactory::createByteTag)
                .collect(Collectors.toList());

        setValue(TagFactory.createListTag(name, tagList));
    }

    public void setShortList(final String name, final List<Short> list) {
        final List<Tag<?>> tagList = list.stream()
                .map(TagFactory::createShortTag)
                .collect(Collectors.toList());

        setValue(TagFactory.createListTag(name, tagList));
    }

    public void setIntList(final String name, final List<Integer> list) {
        final List<Tag<?>> tagList = list.stream()
                .map(TagFactory::createIntTag)
                .collect(Collectors.toList());

        setValue(TagFactory.createListTag(name, tagList));
    }

    public void setLongList(final String name, final List<Long> list) {
        final List<Tag<?>> tagList = list.stream()
                .map(TagFactory::createLongTag)
                .collect(Collectors.toList());

        setValue(TagFactory.createListTag(name, tagList));
    }

    public void setFloatList(final String name, final List<Float> list) {
        final List<Tag<?>> tagList = list.stream()
                .map(TagFactory::createFloatTag)
                .collect(Collectors.toList());

        setValue(TagFactory.createListTag(name, tagList));
    }

    public void setDoubleList(final String name, final List<Double> list) {
        final List<Tag<?>> tagList = list.stream()
                .map(TagFactory::createDoubleTag)
                .collect(Collectors.toList());

        setValue(TagFactory.createListTag(name, tagList));
    }

    public void setStringList(final String name, final List<String> list) {
        final List<Tag<?>> tagList = list.stream()
                .map(TagFactory::createStringTag)
                .collect(Collectors.toList());

        setValue(TagFactory.createListTag(name, tagList));
    }

    public void setByteArrayList(final String name, final List<byte[]> list) {
        final List<Tag<?>> tagList = list.stream()
                .map(TagFactory::createByteArrayTag)
                .collect(Collectors.toList());

        setValue(TagFactory.createListTag(name, tagList));
    }

    public void setIntArrayList(final String name, final List<int[]> list) {
        final List<Tag<?>> tagList = list.stream()
                .map(TagFactory::createIntArrayTag)
                .collect(Collectors.toList());

        setValue(TagFactory.createListTag(name, tagList));
    }

    public boolean getBoolean(final String name) {
        return getByte(name) == 1;
    }

    public byte getByte(final String name) {
        return getValue(name, BYTE, Byte.class);
    }

    public double getDouble(final String name) {
        return getValue(name, DOUBLE, Double.class);
    }

    public float getFloat(final String name) {
        return getValue(name, FLOAT, Float.class);
    }

    public int getInt(final String name) {
        return getValue(name, INTEGER, Integer.class);
    }

    public long getLong(final String name) {
        return getValue(name, LONG, Long.class);
    }

    public short getShort(final String name) {
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
    public List<Boolean> getBooleanList(final String name) {
        return getByteList(name)
                .stream()
                .map(b -> b == 1)
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    public List<Byte> getByteList(final String name) {
        return (List<Byte>) getListValue(name, BYTE);
    }

    @SuppressWarnings("unchecked")
    public List<Short> getShortList(final String name) {
        return (List<Short>) getListValue(name, SHORT);
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