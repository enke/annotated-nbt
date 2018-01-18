package ru.enke.annotated.nbt.stream;

import ru.enke.annotated.nbt.Tag;
import ru.enke.annotated.nbt.TagType;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import static ru.enke.annotated.nbt.TagType.END;

public class NBTOutputStream extends DataOutputStream {

    public NBTOutputStream(OutputStream out) throws IOException {
        this(out, false);
    }

    public NBTOutputStream(OutputStream out, boolean compressed) throws IOException {
        super(compressed ? new GZIPOutputStream(out) : out);
    }

    public void writeTag(final Tag tag) throws IOException {
        final TagType tagType = tag.getType();

        writeTagType(tagType);
        writeUTF(tag.getName());
        writeTag(tagType, tag.getValue());
    }

    private void writeTagType(final TagType type) throws IOException {
        writeByte(type.ordinal());
    }

    @SuppressWarnings("unchecked")
    private void writeTag(final TagType type, final Object value) throws IOException {
        switch(type) {
            case END:
                break;
            case BYTE:
                writeByteTag((byte) value);
                break;
            case SHORT:
                writeShortTag((short) value);
                break;
            case INTEGER:
                writeIntTag((int) value);
                break;
            case LONG:
                writeLongTag((long) value);
                break;
            case FLOAT:
                writeFloatTag((float) value);
                break;
            case DOUBLE:
                writeDoubleTag((double) value);
                break;
            case BYTE_ARRAY:
                writeByteArrayTag((byte[]) value);
                break;
            case STRING:
                writeStringTag((String) value);
                break;
            case LIST:
                writeListTag((List<Tag<?>>) value);
                break;
            case COMPOUND:
                writeCompoundTag((Map<String, Tag<?>>) value);
                break;
            case INTEGER_ARRAY:
                writeIntArrayTag((int[]) value);
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

    private void writeByteTag(final byte value) throws IOException {
        writeByte(value);
    }

    private void writeShortTag(final short value) throws IOException {
        writeShort(value);
    }

    private void writeIntTag(final int value) throws IOException {
        writeInt(value);
    }

    private void writeLongTag(final long value) throws IOException {
        writeLong(value);
    }

    private void writeFloatTag(final float value) throws IOException {
        writeFloat(value);
    }

    private void writeDoubleTag(final double value) throws IOException {
        writeDouble(value);
    }

    private void writeByteArrayTag(final byte[] bytes) throws IOException {
        writeInt(bytes.length);
        write(bytes);
    }

    private void writeStringTag(final String value) throws IOException {
        writeUTF(value);
    }

    private void writeListTag(final List<Tag<?>> tags) throws IOException {
        final TagType elementType = !tags.isEmpty() ? tags.get(0).getType() : END;

        writeTagType(elementType);
        writeInt(tags.size());

        for(final Tag childTag : tags) {
            if(!childTag.getName().equals("")) {
                throw new IllegalArgumentException("List tag element contains tag name");
            }

            writeTag(elementType, childTag.getValue());
        }
    }

    private void writeCompoundTag(final Map<String, Tag<?>> tags) throws IOException {
        for(final Tag childTag : tags.values()) {
            writeTag(childTag);
        }

        writeTagType(END);
    }

    private void writeIntArrayTag(final int[] ints) throws IOException {
        writeInt(ints.length);

        for(final int i : ints) {
            writeInt(i);
        }
    }

}
