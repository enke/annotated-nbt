package ru.enke.annotated.nbt.codec;

import ru.enke.annotated.nbt.Tag;
import ru.enke.annotated.nbt.TagCompound;
import ru.enke.annotated.nbt.TagType;
import ru.enke.annotated.nbt.exception.TagCodecException;
import ru.enke.annotated.nbt.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
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
        final TagCompound tagCompound = new TagCompound(name);
        final Class<?> classType = object.getClass();

        final Map<String, Field> fields = ReflectionUtil.getTagsFields(classType);

        if(fields.isEmpty()) {
            throw new TagCodecException("Class " + classType + " missing NBT annotations");
        }

        for(final Entry<String, Field> entry : fields.entrySet()) {
            final String tagName = entry.getKey();
            final Field field = entry.getValue();

            final Object value = ReflectionUtil.getFieldValue(field, object);

            if(value == null) {
                continue;
            }

            setValueFromField(tagCompound, tagName, value, field);
        }

        return tagCompound;
    }

    @SuppressWarnings("unchecked")
    private void setValueFromField(final TagCompound tagCompound, final String tagName, final Object value, final Field field) throws TagCodecException {
        final Class<?> fieldType = field.getType();
        final TagType tagType = TagType.getTypeByClass(fieldType);

        if(tagType == null) {
            tagCompound.setCompound(encode(tagName, value));
            return;
        }

        switch(tagType) {
            case BYTE:
                if(fieldType == Boolean.class || fieldType == boolean.class) {
                    tagCompound.setBoolean(tagName, (Boolean) value);
                    return;
                }

                tagCompound.setByte(tagName, (Byte) value);
                return;
            case SHORT:
                tagCompound.setShort(tagName, (Short) value);
                return;
            case INTEGER:
                tagCompound.setInt(tagName, (Integer) value);
                return;
            case LONG:
                tagCompound.setLong(tagName, (Long) value);
                return;
            case FLOAT:
                tagCompound.setFloat(tagName, (Float) value);
                return;
            case DOUBLE:
                tagCompound.setDouble(tagName, (Double) value);
                return;
            case BYTE_ARRAY:
                tagCompound.setByteArray(tagName, (byte[]) value);
                return;
            case STRING:
                tagCompound.setString(tagName, (String) value);
                return;
            case LIST:
                final ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
                final Class<?> listClassType = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                final TagType listTagType = TagType.getTypeByClass(listClassType);

                if(listTagType == null) {
                    final List<Object> objectList = (List<Object>) value;
                    final List<Tag<?>> tagList = new ArrayList<>();

                    for(final Object object : objectList) {
                        tagList.add(encode("", object));
                    }

                    tagCompound.setCompoundList(tagName, tagList);
                    return;
                }

                switch(listTagType) {
                    case BYTE:
                        if(listClassType == Boolean.class) {
                            tagCompound.setBooleanList(tagName, (List<Boolean>) value);
                            return;
                        }

                        tagCompound.setByteList(tagName, (List<Byte>) value);
                        return;
                    case SHORT:
                        tagCompound.setShortList(tagName, (List<Short>) value);
                        return;
                    case INTEGER:
                        tagCompound.setIntList(tagName, (List<Integer>) value);
                        return;
                    case LONG:
                        tagCompound.setLongList(tagName, (List<Long>) value);
                        return;
                    case FLOAT:
                        tagCompound.setFloatList(tagName, (List<Float>) value);
                        return;
                    case DOUBLE:
                        tagCompound.setDoubleList(tagName, (List<Double>) value);
                        return;
                    case BYTE_ARRAY:
                        tagCompound.setByteArrayList(tagName, (List<byte[]>) value);
                        return;
                    case STRING:
                        tagCompound.setStringList(tagName, (List<String>) value);
                        return;
                    case COMPOUND:
                        tagCompound.setCompoundList(tagName, (List<Tag<?>>) value);
                        return;
                    case INTEGER_ARRAY:
                        tagCompound.setIntArrayList(tagName, (List<int[]>) value);
                        return;
                    default:
                        throw new UnsupportedOperationException();
                }
            case INTEGER_ARRAY:
                tagCompound.setIntArray(tagName, (int[]) value);
                return;
            default:
                throw new UnsupportedOperationException();
        }
    }

    private Object getValueFromField(final TagCompound tagCompound, final String tagName, final Field field) throws TagCodecException {
        final Class<?> fieldType = field.getType();
        final TagType tagType = TagType.getTypeByClass(fieldType);

        if(tagType == null) {
            return decode(tagCompound.getCompound(tagName), fieldType);
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
                final Class<?> listClassType = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                final TagType listTagType = TagType.getTypeByClass(listClassType);

                if(listTagType == null) {
                    final List<Object> objectList = new ArrayList<>();
                    final List<TagCompound> compoundList = tagCompound.getCompoundList(tagName);

                    for(final TagCompound listTag : compoundList) {
                        objectList.add(decode(listTag, listClassType));
                    }

                    return objectList;
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
                    default:
                        throw new UnsupportedOperationException();
                }
            case INTEGER_ARRAY:
                return tagCompound.getIntArray(tagName);
            default:
                throw new UnsupportedOperationException();
        }
    }

}
