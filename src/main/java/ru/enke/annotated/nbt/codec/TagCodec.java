package ru.enke.annotated.nbt.codec;

import ru.enke.annotated.nbt.TagCompound;
import ru.enke.annotated.nbt.exception.TagCodecException;

public interface TagCodec {

    <T> T decode(final TagCompound tag, final Class<T> classType) throws TagCodecException;

    TagCompound encode(final String name, final Object object) throws TagCodecException;

}
