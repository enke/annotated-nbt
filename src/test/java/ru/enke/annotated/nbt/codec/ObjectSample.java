package ru.enke.annotated.nbt.codec;

import ru.enke.annotated.nbt.annotation.NBT;

import java.util.List;

class ObjectSample {

    @NBT("boolean")
    boolean sampleBoolean;

    @NBT("byte")
    byte sampleByte;

    @NBT("short")
    short sampleShort;

    @NBT("int")
    int sampleInt;

    @NBT("long")
    long sampleLong;

    @NBT("float")
    float sampleFloat;

    @NBT("double")
    double sampleDouble;

    @NBT("string")
    String sampleString;

    String skipString;

    @NBT("byteArray")
    byte[] sampleByteArray;

    @NBT("intArray")
    int[] sampleIntArray;

    @NBT("booleanList")
    List<Boolean> sampleBooleanList;

    @NBT("byteList")
    List<Byte> sampleByteList;

    @NBT("shortList")
    List<Short> sampleShortList;

    @NBT("intList")
    List<Integer> sampleIntList;

    @NBT("longList")
    List<Long> sampleLongList;

    @NBT("floatList")
    List<Float> sampleFloatList;

    @NBT("doubleList")
    List<Double> sampleDoubleList;

    @NBT("stringList")
    List<String> sampleStringList;

    @NBT("byteArrayList")
    List<byte[]> sampleByteArrayList;

    @NBT("intArrayList")
    List<int[]> sampleIntArrayList;

    @NBT("object")
    ObjectSample sampleObject;

    @NBT("objectList")
    List<ObjectSample> sampleObjectList;

}
