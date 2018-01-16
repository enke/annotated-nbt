package ru.enke.annotated.nbt;

import org.junit.After;
import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import ru.enke.annotated.nbt.stream.NBTInputStream;
import ru.enke.annotated.nbt.stream.NBTOutputStream;
import ru.enke.annotated.nbt.tag.Tag;
import ru.enke.annotated.nbt.tag.TagFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(Theories.class)
public class TagsTest {

    private static final Map<String, Tag<?>> compoundTag = createCompoundTag();

    private static Map<String, Tag<?>> createCompoundTag() {
        final Map<String, Tag<?>> compoundTag = new HashMap<>();
        compoundTag.put("key", TagFactory.createStringTag("key", "value"));
        compoundTag.put("key2", TagFactory.createStringTag("key2", "value2"));

        return compoundTag;
    }

    @DataPoints("tags")
    public static final Tag[] tags = new Tag[]{
            TagFactory.createByteTag("byte", (byte) 1),
            TagFactory.createShortTag("short", (short) 2),
            TagFactory.createIntTag("int", 3),
            TagFactory.createLongTag("long", 4L),
            TagFactory.createFloatTag("float", 5.1F),
            TagFactory.createDoubleTag("double", 6.33D),
            TagFactory.createByteArrayTag("bytes", new byte[] {7, 8, -9}),
            TagFactory.createStringTag("string", "hello world"),
            TagFactory.createListTag("list", Collections.singletonList(TagFactory.createStringTag("value"))),
            TagFactory.createCompoundTag("compound", compoundTag),
            TagFactory.createIntArrayTag("ints", new int[] {-10, 11, 12})
    };

    private NBTOutputStream out;
    private NBTInputStream in;

    @Before
    public void prepareStreams() throws IOException {
        final File file = File.createTempFile("tagsTest", ".tmp");
        file.deleteOnExit();

        out = new NBTOutputStream(new FileOutputStream(file));
        in = new NBTInputStream(new FileInputStream(file));
    }

    @SuppressWarnings("unchecked")
    @Theory
    public void testTags(@FromDataPoints("tags") final Tag outTag) throws IOException {
        out.writeTag(outTag);
        assertEquals(outTag, in.readTag());
    }

    @After
    public void closeStreams() throws IOException {
        in.close();
        out.close();
    }

}
