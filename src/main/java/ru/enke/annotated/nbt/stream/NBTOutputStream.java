package ru.enke.annotated.nbt.stream;

import ru.enke.annotated.nbt.tag.Tag;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

public class NBTOutputStream extends DataOutputStream {

    public NBTOutputStream(OutputStream out) throws IOException {
        this(out, false);
    }

    public NBTOutputStream(OutputStream out, boolean compressed) throws IOException {
        super(compressed ? new GZIPOutputStream(out) : out);
    }

    @SuppressWarnings("unchecked")
    public void writeTag(final Tag tag) throws IOException {
        final Tag.Type type = tag.getType();
        final Object value = tag.getValue();

        writeByte(type.ordinal());
        writeUTF(tag.getName());

        switch(type) {
            case END:
                break;
            case BYTE:
                writeByte((byte) value);
                break;
            case SHORT:
                writeShort((short) value);
                break;
            case INTEGER:
                writeInt((int) value);
                break;
            case LONG:
                writeLong((long) value);
                break;
            case FLOAT:
                writeFloat((float) value);
                break;
            case DOUBLE:
                writeDouble((double) value);
                break;
            case BYTE_ARRAY:
                byte[] bytes = (byte[]) value;

                writeInt(bytes.length);
                write(bytes);
                break;
            case STRING:
                writeUTF((String) value);
                break;
            case LIST:
                final List<Tag> list = (List<Tag>) value;

                if(!list.isEmpty()) {
                    writeByte(list.get(0).getType().ordinal());
                } else {
                    writeByte(0);
                }

                writeInt(list.size());

                for(final Tag t : list) {
                    writeTag(t);
                }

                break;
            case COMPOUND:
                final Map<String, Tag> map = (Map<String, Tag>) value;

                for(final Tag t : map.values()) {
                    writeTag(t);
                }

                writeByte(0);
                break;
            case INTEGER_ARRAY:
                final int[] ints = (int[]) value;
                writeInt(ints.length);

                for(final int i : ints) {
                    writeInt(i);
                }

                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

}
