package ru.enke.annotated.nbt.codec;

import org.junit.Test;
import ru.enke.annotated.nbt.TagCompound;
import ru.enke.annotated.nbt.exception.TagCodecException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

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
    public void testDecodeBooleanList() throws TagCodecException {
        final List<Boolean> booleanList = Arrays.asList(true, true, false, false, true);

        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setBooleanList("booleanList", booleanList);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());
        assertEquals(booleanList, object.sampleBooleanList);
    }

    @Test
    public void testDecodeByteList() throws TagCodecException {
        final List<Byte> byteList = Arrays.asList((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);

        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setByteList("byteList", byteList);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());
        assertEquals(byteList, object.sampleByteList);
    }

    @Test
    public void testDecodeShortList() throws TagCodecException {
        final List<Short> shortList = Arrays.asList((short) 1, (short) 2, (short) 3, (short) 4, (short) 5);

        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setShortList("shortList", shortList);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());
        assertEquals(shortList, object.sampleShortList);
    }

    @Test
    public void testDecodeIntList() throws TagCodecException {
        final List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);

        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setIntList("intList", intList);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());
        assertEquals(intList, object.sampleIntList);
    }

    @Test
    public void testDecodeLongList() throws TagCodecException {
        final List<Long> longList = Arrays.asList(1L, 2L, 3L, 4L, 5L);

        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setLongList("longList", longList);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());
        assertEquals(longList, object.sampleLongList);
    }

    @Test
    public void testDecodeFloatList() throws TagCodecException {
        final List<Float> floatList = Arrays.asList(1.2F, 2.3F, 3.4F, 4.5F, 5.6F);

        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setFloatList("floatList", floatList);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());
        assertEquals(floatList, object.sampleFloatList);
    }

    @Test
    public void testDecodeDoubleList() throws TagCodecException {
        final List<Double> doubleList = Arrays.asList(1.2, 2.3, 3.4, 4.5, 5.6);

        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setDoubleList("doubleList", doubleList);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());
        assertEquals(doubleList, object.sampleDoubleList);
    }

    @Test
    public void testDecodeStringList() throws TagCodecException {
        final List<String> stringList = Arrays.asList("a", "b", "c", "d");

        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setStringList("stringList", stringList);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());
        assertEquals(stringList, object.sampleStringList);
    }

    @Test
    public void testDecodeByteArrayList() throws TagCodecException {
        final List<byte[]> byteArrayList = Arrays.asList(new byte[] {1, 3, 5, 2, 3}, new byte[] {1, 3, 5, 2, 3});

        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setByteArrayList("byteArrayList", byteArrayList);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());
        assertEquals(byteArrayList, object.sampleByteArrayList);
    }

    @Test
    public void testDecodeIntArrayList() throws TagCodecException {
        final List<int[]> intArrayList = Arrays.asList(new int[] {1, 3, 5, 2, 3}, new int[] {1, 3, 5, 2, 3});

        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setIntArrayList("intArrayList", intArrayList);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());
        assertEquals(intArrayList, object.sampleIntArrayList);
    }

    @Test
    public void testDecodeObject() throws TagCodecException {
        final TagCompound innerCompound = new TagCompound("object");
        innerCompound.setBoolean("boolean", true);
        innerCompound.setLong("long", 35L);
        innerCompound.setString("string", "abcd");

        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setCompound(innerCompound);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());

        final ObjectSample innerObject = object.sampleObject;

        assertNotNull(innerObject);
        assertTrue(innerObject.sampleBoolean);
        assertEquals(35, innerObject.sampleLong);
        assertEquals("abcd", innerObject.sampleString);
    }

    @Test
    public void testDecodeObjectList() throws TagCodecException {
        final TagCompound innerCompound = new TagCompound();
        innerCompound.setBoolean("boolean", true);
        innerCompound.setLong("long", 35L);
        innerCompound.setString("string", "abcd");

        final TagCompound innerCompound2 = new TagCompound();
        innerCompound2.setInt("int", 773);
        innerCompound2.setDouble("double", 3.4344);

        final TagCompound tagCompound = new TagCompound("test");
        tagCompound.setCompoundList("objectList", innerCompound, innerCompound2);

        final ObjectSample object = codec.decode(tagCompound, ObjectSample.class);

        assertNotNull(object);
        assertEquals(1, tagCompound.getSize());

        final List<ObjectSample> objectList = object.sampleObjectList;

        assertNotNull(objectList);
        assertEquals(2, objectList.size());

        final ObjectSample innerObject = objectList.get(0);

        assertTrue(innerObject.sampleBoolean);
        assertEquals(35, innerObject.sampleLong);
        assertEquals("abcd", innerObject.sampleString);

        final ObjectSample innerObject2 = objectList.get(1);

        assertEquals(773, innerObject2.sampleInt);
        assertEquals(3.4344, innerObject2.sampleDouble, 0);
    }

    @Test
    public void testEncodeBoolean() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleBoolean = true;

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(object.sampleBoolean, tagCompound.getBoolean("boolean"));
    }

    @Test
    public void testEncodeByte() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleByte = 1;

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(object.sampleByte, tagCompound.getByte("byte"));
    }

    @Test
    public void testEncodeShort() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleShort = 23;

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(object.sampleShort, tagCompound.getShort("short"));
    }

    @Test
    public void testEncodeInt() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleInt = 69;

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(object.sampleInt, tagCompound.getInt("int"));
    }

    @Test
    public void testEncodeDouble() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleDouble = 3.445;

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(object.sampleDouble, tagCompound.getDouble("double"), 0);
    }

    @Test
    public void testEncodeFloat() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleFloat = 5.6F;

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(object.sampleFloat, tagCompound.getFloat("float"), 0);
    }

    @Test
    public void testEncodeLong() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleLong = 87900876675L;

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(object.sampleLong, tagCompound.getLong("long"), 0);
    }

    @Test
    public void testEncodeString() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleString = "value";

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(object.sampleString, tagCompound.getString("string"));
    }

    @Test
    public void testEncodeSkip() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.skipString = "test2";

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertFalse(tagCompound.containsTag("test2"));
    }

    @Test
    public void testEncodeByteArray() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleByteArray = new byte[] {1, 3, 5, 2, 3};

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(object.sampleByteArray, tagCompound.getByteArray("byteArray"));
    }

    @Test
    public void testEncodeIntArray() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleIntArray = new int[] {1, 3, 5, 2, 3};

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(object.sampleIntArray, tagCompound.getIntArray("intArray"));
    }

    @Test
    public void testEncodeBooleanList() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleBooleanList = Arrays.asList(true, true, false, false, true);

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(object.sampleBooleanList, tagCompound.getBooleanList("booleanList"));
    }

    @Test
    public void testEncodeByteList() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleByteList = Arrays.asList((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);;

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(object.sampleByteList, tagCompound.getByteList("byteList"));
    }

    @Test
    public void testEncodeShortList() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleShortList = Arrays.asList((short) 1, (short) 2, (short) 3, (short) 4, (short) 5);

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(object.sampleShortList, tagCompound.getShortList("shortList"));
    }

    @Test
    public void testEncodeIntList() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleIntList = Arrays.asList(1, 2, 3, 4, 5);

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(object.sampleIntList, tagCompound.getIntList("intList"));
    }

    @Test
    public void testEncodeLongList() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleLongList = Arrays.asList(1L, 2L, 3L, 4L, 5L);

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(object.sampleLongList, tagCompound.getLongList("longList"));
    }

    @Test
    public void testEncodeFloatList() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleFloatList = Arrays.asList(1.2F, 2.3F, 3.4F, 4.5F, 5.6F);

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(object.sampleFloatList, tagCompound.getFloatList("floatList"));
    }

    @Test
    public void testEncodeDoubleList() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleDoubleList = Arrays.asList(1.2, 2.3, 3.4, 4.5, 5.6);

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(object.sampleDoubleList, tagCompound.getDoubleList("doubleList"));
    }

    @Test
    public void testEncodeStringList() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleStringList = Arrays.asList("a", "b", "c", "d");

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(object.sampleStringList, tagCompound.getStringList("stringList"));
    }


    @Test
    public void testEncodeByteArrayList() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleByteArrayList = Arrays.asList(new byte[] {1, 3, 5, 2, 3}, new byte[] {1, 3, 5, 2, 3});

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(object.sampleByteArrayList, tagCompound.getByteArrayList("byteArrayList"));
    }

    @Test
    public void testEncodeIntArrayList() throws TagCodecException {
        final ObjectSample object = new ObjectSample();
        object.sampleIntArrayList = Arrays.asList(new int[] {1, 3, 5, 2, 3}, new int[] {1, 3, 5, 2, 3});

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);
        assertEquals(object.sampleIntArrayList, tagCompound.getIntArrayList("intArrayList"));
    }

    @Test
    public void testEncodeObject() throws TagCodecException {
        final ObjectSample innerObject = new ObjectSample();
        innerObject.sampleBoolean = true;
        innerObject.sampleLong = 35L;
        innerObject.sampleString = "abcd";

        final ObjectSample object = new ObjectSample();
        object.sampleObject = innerObject;

        final TagCompound tagCompound = codec.encode("test", object);

        assertNotNull(tagCompound);

        final TagCompound innerCompound = tagCompound.getCompound("object");

        assertNotNull(innerCompound);
        assertEquals(innerObject.sampleBoolean, innerCompound.getBoolean("boolean"));
        assertEquals(innerObject.sampleLong, innerCompound.getLong("long"));
        assertEquals(innerObject.sampleString, innerCompound.getString("string"));
    }

    @Test
    public void testEncodeObjectList() throws TagCodecException {
        final ObjectSample innerObject = new ObjectSample();
        innerObject.sampleBoolean = true;
        innerObject.sampleLong = 35L;
        innerObject.sampleString = "abcd";

        final ObjectSample innerObject2 = new ObjectSample();
        innerObject2.sampleInt = 35;
        innerObject2.sampleFloat = 3.54F;

        final ObjectSample object = new ObjectSample();
        object.sampleObjectList = Arrays.asList(innerObject, innerObject2);

        final TagCompound tagCompound = codec.encode("test", object);
        assertNotNull(tagCompound);

        final List<TagCompound> objectList = tagCompound.getCompoundList("objectList");
        assertNotNull(objectList);

        final TagCompound innerCompound = objectList.get(0);

        assertEquals(innerObject.sampleBoolean, innerCompound.getBoolean("boolean"));
        assertEquals(innerObject.sampleLong, innerCompound.getLong("long"));
        assertEquals(innerObject.sampleString, innerCompound.getString("string"));

        final TagCompound innerCompound2 = objectList.get(1);

        assertEquals(innerObject2.sampleInt, innerCompound2.getInt("int"));
        assertEquals(innerObject2.sampleFloat, innerCompound2.getFloat("float"), 0);
    }

}
