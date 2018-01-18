package ru.enke.annotated.nbt.codec;

import ru.enke.annotated.nbt.TagCompound;
import ru.enke.annotated.nbt.TagType;
import ru.enke.annotated.nbt.exception.TagCodecException;
import ru.enke.annotated.nbt.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

public class ReflectionCodec implements TagCodec {

    @Override
    public <T> T decode(final TagCompound tag, final Class<T> classType) throws TagCodecException {
        final Map<String, Field> fields = ReflectionUtil.getTagsFields(classType);
        final T object = ReflectionUtil.createObject(classType);

        if(fields.isEmpty()) {
            throw new TagCodecException("Class " + classType + " missing NBT annotations");
        }

        for(final Entry<String, Field> entry : fields.entrySet()) {
            final String tagName = entry.getKey();

            if(!tag.containsTag(tagName)) {
                continue;
            }

            final Field field = entry.getValue();
            final Class<?> fieldType = field.getType();

            ReflectionUtil.setFieldValue(field, object, getValue(tag, tagName, fieldType));
        }

        return object;
    }

    @Override
    public TagCompound encode(final String name, final Object object) throws TagCodecException {
        return new TagCompound();
    }

    private Object getValue(final TagCompound tagCompound, final String tagName, final Class<?> fieldType) {
        final TagType type = TagType.getTypeByClass(fieldType);

        if(type == null) {
            return tagCompound.getCompound(tagName);
        }

        switch(type) {
            case BYTE:
                if(fieldType == Boolean.class || fieldType == boolean.class) {
                    return tagCompound.getBoolean(tagName);
                }

                return tagCompound.getByte(tagName);
            case SHORT:
                return tagCompound.getShort(tagName);
            case INTEGER:
                return tagCompound.getInt(tagName);
            case LONG:
                return tagCompound.getLong(tagName);
            case FLOAT:
                return tagCompound.getFloat(tagName);
            case DOUBLE:
                return tagCompound.getDouble(tagName);
            case BYTE_ARRAY:
                return tagCompound.getByteArray(tagName);
            case STRING:
                return tagCompound.getString(tagName);
            case LIST:
                return null;
            case COMPOUND:
                return null;
            case INTEGER_ARRAY:
                return tagCompound.getIntArray(tagName);
            default:
                throw new UnsupportedOperationException();
        }
    }

}
