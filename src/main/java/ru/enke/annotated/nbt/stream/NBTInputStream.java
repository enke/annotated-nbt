package ru.enke.annotated.nbt.stream;

import org.jetbrains.annotations.Nullable;
import ru.enke.annotated.nbt.tag.Tag;
import ru.enke.annotated.nbt.tag.TagFactory;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import static ru.enke.annotated.nbt.tag.Tag.Type.END;

public class NBTInputStream extends DataInputStream {

    private final boolean compressed;

    public NBTInputStream(final InputStream in) throws IOException {
        this(in, false);
    }

    public NBTInputStream(final InputStream in, final boolean compressed) throws IOException {
        super(compressed ? new GZIPInputStream(in) : in);
        this.compressed = compressed;
    }

    @Nullable
    public Tag<?> readTag() throws IOException {
        return readTag(readTagType(), true);
    }

    private Tag.Type readTagType() throws IOException {
        final int typeId = readUnsignedByte();
        final Tag.Type[] types = Tag.Type.values();

        if(typeId < 0 | typeId > types.length) {
            if(typeId == 31 && !compressed) {
                throw new IllegalArgumentException("Reading compressed stream in uncompressed mode");
            }

            throw new IllegalArgumentException("Unknown tag type " + typeId);
        }

        return types[typeId];
    }

    @Nullable
    private Tag<?> readTag(final Tag.Type type, final boolean hasName) throws IOException {
        switch(type) {
            case END:
                return null;
            case BYTE:
                return readByteTag(hasName);
            case SHORT:
                return readShortTag(hasName);
            case INTEGER:
                return readIntTag(hasName);
            case LONG:
                return readLongTag(hasName);
            case FLOAT:
                return readFloatTag(hasName);
            case DOUBLE:
                return readDoubleTag(hasName);
            case BYTE_ARRAY:
                return readByteArrayTag(hasName);
            case STRING:
                return readStringTag(hasName);
            case LIST:
                return readListTag(hasName);
            case COMPOUND:
                return readCompoundTag(hasName);
            case INTEGER_ARRAY:
                return readIntArrayTag(hasName);
            default:
                throw new UnsupportedOperationException();
        }
    }

    private Tag<Byte> readByteTag(final boolean hasName) throws IOException {
        final String name = hasName ? readUTF() : "";
        final byte value = readByte();

        return TagFactory.createByteTag(name, value);
    }

    private Tag<Short> readShortTag(final boolean hasName) throws IOException {
        final String name = hasName ? readUTF() : "";
        final short value = readShort();

        return TagFactory.createShortTag(name, value);
    }

    private Tag<Integer> readIntTag(final boolean hasName) throws IOException {
        final String name = hasName ? readUTF() : "";
        final int value = readInt();

        return TagFactory.createIntTag(name, value);
    }

    private Tag<Long> readLongTag(final boolean hasName) throws IOException {
        final String name = hasName ? readUTF() : "";
        final long value = readLong();

        return TagFactory.createLongTag(name, value);
    }

    private Tag<Float> readFloatTag(final boolean hasName) throws IOException {
        final String name = hasName ? readUTF() : "";
        final float value = readFloat();

        return TagFactory.createFloatTag(name, value);
    }

    private Tag<Double> readDoubleTag(final boolean hasName) throws IOException {
        final String name = hasName ? readUTF() : "";
        final double value = readDouble();

        return TagFactory.createDoubleTag(name, value);
    }

    private Tag<byte[]> readByteArrayTag(final boolean hasName) throws IOException {
        final String name = hasName ? readUTF() : "";
        final int length = readInt();
        final byte[] bytes = new byte[length];

        for(int i = 0; i < length; i++) {
            bytes[i] = readByte();
        }

        return TagFactory.createByteArrayTag(name, bytes);
    }

    private Tag<String> readStringTag(final boolean hasName) throws IOException {
        final String name = hasName ? readUTF() : "";
        final String value = readUTF();

        return TagFactory.createStringTag(name, value);
    }

    private Tag<List<Tag<?>>> readListTag(final boolean hasName) throws IOException {
        final String name = hasName ? readUTF() : "";
        final Tag.Type type = readTagType();

        if(type == END) {
            return null;
        }

        final List<Tag<?>> tags = new ArrayList<>();
        final int listSize = readInt();

        for(int i = 0; i < listSize; i++) {
            tags.add(readTag(type, false));
        }

        return TagFactory.createListTag(name, tags);
    }

    private Tag<Map<String, Tag<?>>> readCompoundTag(final boolean hasName) throws IOException {
        final Map<String, Tag<?>> map = new HashMap<>();
        final String name = hasName ? readUTF() : "";
        Tag childTag;

        while((childTag = readTag()) != null) {
            final String childName = childTag.getName();
            map.put(childName, childTag);
        }

        return TagFactory.createCompoundTag(name, map);
    }

    private Tag<int[]> readIntArrayTag(final boolean hasName) throws IOException {
        final String name = hasName ? readUTF() : "";
        final int length = readInt();
        final int[] ints = new int[length];

        for(int i = 0; i < length; i++) {
            ints[i] = readInt();
        }

        return TagFactory.createIntArrayTag(name, ints);
    }

}
