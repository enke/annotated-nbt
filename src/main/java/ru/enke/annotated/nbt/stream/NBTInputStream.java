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

    public NBTInputStream(final InputStream in) throws IOException {
        this(in, false);
    }

    public NBTInputStream(final InputStream in, final boolean compressed) throws IOException {
        super(compressed ? new GZIPInputStream(in) : in);
    }

    @Nullable
    public Tag readTag() throws IOException {
        final Tag.Type type = readTagType();

        if(type == END) {
            return null;
        }

        return readTag(type, readUTF());
    }

    private Tag.Type readTagType() throws IOException {
        final int typeId = readUnsignedByte();
        final Tag.Type[] types = Tag.Type.values();

        if(typeId < 0 | typeId > types.length) {
            throw new IllegalArgumentException("Unknown tag type " + typeId);
        }

        return types[typeId];
    }

    @Nullable
    private Tag readTag(final Tag.Type type, final String name) throws IOException {
        switch(type) {
            case BYTE:
                return TagFactory.createByteTag(name, readByte());
            case SHORT:
                return TagFactory.createShortTag(name, readShort());
            case INTEGER:
                return TagFactory.createIntTag(name, readInt());
            case LONG:
                return TagFactory.createLongTag(name, readLong());
            case FLOAT:
                return TagFactory.createFloatTag(name, readFloat());
            case DOUBLE:
                return TagFactory.createDoubleTag(name, readDouble());
            case BYTE_ARRAY:
                final int bytesLength = readInt();
                final byte[] bytes = new byte[bytesLength];

                for(int i = 0; i < bytesLength; i++) {
                    bytes[i] = readByte();
                }

                return TagFactory.createByteArrayTag(name, bytes);
            case STRING:
                return TagFactory.createStringTag(name, readUTF());
            case LIST:
                final Tag.Type listType = readTagType();

                if(listType == END) {
                    return null;
                }

                final List<Tag<?>> list = new ArrayList<>();
                final int listSize = readInt();

                for(int i = 0; i < listSize; i++) {
                    list.add(readTag(listType, ""));
                }

                return TagFactory.createListTag(name, list);
            case COMPOUND:
                final Map<String, Tag<?>> map = new HashMap<>();
                Tag tag;

                while((tag = readTag()) != null) {
                    map.put(tag.getName(), tag);
                }

                return TagFactory.createCompoundTag(name, map);
            case INTEGER_ARRAY:
                final int intsLength = readInt();
                final int[] ints = new int[intsLength];

                for(int i = 0; i < intsLength; i++) {
                    ints[i] = readInt();
                }

                return TagFactory.createIntArrayTag(name, ints);
            default:
                throw new UnsupportedOperationException();
        }
    }

}
