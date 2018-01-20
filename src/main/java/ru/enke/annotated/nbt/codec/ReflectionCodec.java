package ru.enke.annotated.nbt.codec;

import ru.enke.annotated.nbt.TagCompound;
import ru.enke.annotated.nbt.TagType;
import ru.enke.annotated.nbt.exception.TagCodecException;
import ru.enke.annotated.nbt.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
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
            ReflectionUtil.setFieldValue(field, object, getValueFromField(tag, tagName, field));
        }

        return object;
    }

    @Override
    public TagCompound encode(final String name, final Object object) throws TagCodecException {
        return new TagCompound();
    }

    private Object getValueFromField(final TagCompound tagCompound, final String tagName, final Field field) {
        final Class<?> fieldType = field.getType();
        final TagType tagType = TagType.getTypeByClass(fieldType);

        if(tagType == null) {
            return tagCompound.getCompound(tagName);
        }

        switch(tagType) {
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
                final ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
                Class<?> listClassType = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                final TagType listTagType = TagType.getTypeByClass(listClassType);

                if(listTagType == null) {
                    return tagCompound.getCompoundList(tagName);
                }

                switch(listTagType) {
                    case BYTE:
                        if(listClassType == Boolean.class) {
                            return tagCompound.getBooleanList(tagName);
                        }

                        return tagCompound.getByteList(tagName);
                    case SHORT:
                        return tagCompound.getShortList(tagName);
                    case INTEGER:
                        return tagCompound.getIntList(tagName);
                    case LONG:
                        return tagCompound.getLongList(tagName);
                    case FLOAT:
                        return tagCompound.getFloatList(tagName);
                    case DOUBLE:
                        return tagCompound.getDoubleList(tagName);
                    case BYTE_ARRAY:
                        return tagCompound.getByteArrayList(tagName);
                    case STRING:
                        return tagCompound.getStringList(tagName);
                    case COMPOUND:
                        return tagCompound.getCompoundList(tagName);
                    case INTEGER_ARRAY:
                        return tagCompound.getIntArrayList(tagName);
                }

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
