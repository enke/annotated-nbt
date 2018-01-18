package ru.enke.annotated.nbt.codec;

import org.junit.Test;
import ru.enke.annotated.nbt.TagCompound;
import ru.enke.annotated.nbt.exception.TagCodecException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ReflectionCodecTest {

    private final TagCodec codec = new ReflectionCodec();

    @Test
    public void testDecodeBoolean() throws TagCodecException {
        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setBoolean("boolean", true);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());
        assertTrue(object.sampleBoolean);
    }

    @Test
    public void testDecodeByte() throws TagCodecException {
        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setByte("byte", (byte) -1);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());
        assertEquals((byte) -1, object.sampleByte);
    }

    @Test
    public void testDecodeShort() throws TagCodecException {
        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setShort("short", (short) 23);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());
        assertEquals((short) 23, object.sampleShort);
    }

    @Test
    public void testDecodeInt() throws TagCodecException {
        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setInt("int", Integer.MAX_VALUE);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());
        assertEquals(Integer.MAX_VALUE, object.sampleInt);
    }

    @Test
    public void testDecodeDouble() throws TagCodecException {
        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setDouble("double", Double.MAX_VALUE);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());
        assertEquals(Double.MAX_VALUE, object.sampleDouble, 0);
    }

    @Test
    public void testDecodeFloat() throws TagCodecException {
        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setFloat("float", Float.MAX_VALUE);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());
        assertEquals(Float.MAX_VALUE, object.sampleFloat, 0);
    }

    @Test
    public void testDecodeLong() throws TagCodecException {
        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setLong("long", Long.MAX_VALUE);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());
        assertEquals(Long.MAX_VALUE, object.sampleLong);
    }

    @Test
    public void testDecodeString() throws TagCodecException {
        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setString("string", "value");

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());
        assertEquals("value", object.sampleString);
    }

    @Test
    public void testDecodeByteArray() throws TagCodecException {
        final byte[] byteArray = new byte[] {1, 3, 5, 2, 3};

        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setByteArray("byteArray", byteArray);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());
        assertEquals(byteArray, object.sampleByteArray);
    }

    @Test
    public void testDecodeIntArray() throws TagCodecException {
        final int[] intArray = new int[] {1, 3, 5, 2, 3};

        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setIntArray("intArray", intArray);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());
        assertEquals(intArray, object.sampleIntArray);
    }

    @Test
    public void testEncodeEmpty() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals("test", tagCompound.getName());
        assertEquals(0, tagCompound.getSize());
    }

    @Test
    public void testEncodeBoolean() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleBoolean = true;

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(1, tagCompound.getSize());
        assertEquals(object.sampleBoolean, tagCompound.getBoolean("boolean"));
    }

    @Test
    public void testEncodeByte() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleByte = 1;

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(1, tagCompound.getSize());
        assertEquals(object.sampleByte, tagCompound.getByte("byte"));
    }

    @Test
    public void testEncodeShort() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleShort = 23;

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(1, tagCompound.getSize());
        assertEquals(object.sampleShort, tagCompound.getShort("short"));
    }

    @Test
    public void testEncodeInt() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleInt = 69;

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(1, tagCompound.getSize());
        assertEquals(object.sampleInt, tagCompound.getInt("int"));
    }

    @Test
    public void testEncodeDouble() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleDouble = 3.445;

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(1, tagCompound.getSize());
        assertEquals(object.sampleDouble, tagCompound.getInt("double"), 0);
    }

    @Test
    public void testEncodeFloat() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleFloat = 5.6F;

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(1, tagCompound.getSize());
        assertEquals(object.sampleFloat, tagCompound.getFloat("float"), 0);
    }

    @Test
    public void testEncodeLong() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleLong = 87900876675L;

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(1, tagCompound.getSize());
        assertEquals(object.sampleLong, tagCompound.getLong("long"), 0);
    }

    @Test
    public void testEncodeString() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleString = "value";

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(1, tagCompound.getSize());
        assertEquals(object.sampleString, tagCompound.getString("test"));
    }

    @Test
    public void testEncodeSkip() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.skipString = "test2";

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(0, tagCompound.getSize());
    }

}
