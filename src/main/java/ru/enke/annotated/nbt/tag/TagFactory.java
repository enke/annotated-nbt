package ru.enke.annotated.nbt.tag;

import java.util.List;
import java.util.Map;

import static ru.enke.annotated.nbt.tag.Tag.Type.*;

public class TagFactory {

    private TagFactory() {}

    public static Tag<Byte> createByteTag(final String name, final byte value) {
        return new Tag<>(name, value, BYTE);
    }

    public static Tag<Short> createShortTag(final String name, final short value) {
        return new Tag<>(name, value, SHORT);
    }

    public static Tag<Integer> createIntTag(final String name, final int value) {
        return new Tag<>(name, value, INTEGER);
    }

    public static Tag<Long> createLongTag(final String name, final long value) {
        return new Tag<>(name, value, LONG);
    }

    public static Tag<Float> createFloatTag(final String name, final float value) {
        return new Tag<>(name, value, FLOAT);
    }

    public static Tag<Double> createDoubleTag(final String name, final double value) {
        return new Tag<>(name, value, DOUBLE);
    }

    public static Tag<byte[]> createByteArrayTag(final String name, final byte[] value) {
        return new Tag<>(name, value, BYTE_ARRAY);
    }

    public static Tag<String> createStringTag(final String name, final String value) {
        return new Tag<>(name, value, STRING);
    }

    public static Tag<List<Tag<?>>> createListTag(final String name, final List<Tag<?>> value) {
        return new Tag<>(name, value, LIST);
    }

    public static Tag<Map<String, Tag<?>>> createCompoundTag(final String name, final Map<String, Tag<?>> value) {
        return new Tag<>(name, value, COMPOUND);
    }

    public static Tag<int[]> createIntArrayTag(final String name, final int[] value) {
        return new Tag<>(name, value, INTEGER_ARRAY);
    }

}
