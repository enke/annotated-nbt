package ru.enke.annotated.nbt;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.enke.annotated.nbt.stream.NBTInputStream;
import ru.enke.annotated.nbt.tag.TagCompound;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BigTest {

    private static final String FILE_LOCATION = "src/test/resources/bigtest.dat";
    private static TagCompound tag;

    @BeforeClass
    public static void setUp() throws IOException {
        try(NBTInputStream inputStream = new NBTInputStream(new FileInputStream(FILE_LOCATION), true)) {
            tag = inputStream.readTagCompound();
        }
    }

    @Test
    public void testByte() {
        assertEquals((byte) 127, tag.getByte("byteTest"));
    }

    @Test
    public void testDouble() {
        assertEquals(0.4931287132182315D, tag.getDouble("doubleTest"), 0);
    }

    @Test
    public void testFloat() {
        assertEquals(0.4982314705848694F, tag.getFloat("floatTest"), 0);
    }

    @Test
    public void testInt() {
        assertEquals(Integer.MAX_VALUE, tag.getInt("intTest"));
    }

    @Test
    public void testLong() {
        assertEquals(9223372036854775807L, tag.getLong("longTest"));
    }

    @Test
    public void testShort() {
        assertEquals(Short.MAX_VALUE, tag.getShort("shortTest"));
    }

    @Test
    public void testString() {
        assertEquals("HELLO WORLD THIS IS A TEST STRING ÅÄÖ!", tag.getString("stringTest"));
    }

    @Test
    public void testByteArray() {
        final byte[] bytes = tag.getByteArray("byteArrayTest");

        for(int i = 0; i < 1000; i++) {
            assertEquals((i * i * 255 + i * 7) % 100, bytes[i]);
        }
    }

    @Test
    public void testLongList() {
        final List<Long> longs = tag.getLongList("longListTest");
        assertEquals(5, longs.size());

        assertEquals(11, (long) longs.get(0));
        assertEquals(12, (long) longs.get(1));
        assertEquals(13, (long) longs.get(2));
        assertEquals(14, (long) longs.get(3));
        assertEquals(15, (long) longs.get(4));
    }

    @Test
    public void testCompoundList() {
        final List<TagCompound> tagCompounds = tag.getCompoundList("compoundListTest");
        assertEquals(2, tagCompounds.size());

        final TagCompound firstEntryTag = tagCompounds.get(0);
        assertEquals(2, firstEntryTag.getSize());

        assertEquals(1264099775885L, firstEntryTag.getLong("created-on"));
        assertEquals("Compound tag #0", firstEntryTag.getString("name"));

        final TagCompound secondEntryTag = tagCompounds.get(1);
        assertEquals(2, secondEntryTag.getSize());

        assertEquals(1264099775885L, secondEntryTag.getLong("created-on"));
        assertEquals("Compound tag #1", secondEntryTag.getString("name"));
    }

    @Test
    public void testNestedCompound() {
        final TagCompound nestedTag = tag.getCompound("nestedCompoundTest");
        final TagCompound eggTag = nestedTag.getCompound("egg");

        assertEquals("Eggbert", eggTag.getString("name"));
        assertEquals(0.5, eggTag.getFloat("value"), 0);

        final TagCompound hamTag = nestedTag.getCompound("ham");

        assertEquals("Hampus", hamTag.getString("name"));
        assertEquals(0.75, hamTag.getFloat("value"), 0);
    }

}
