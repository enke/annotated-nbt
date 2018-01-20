package ru.enke.annotated.nbt.codec;

import ru.enke.annotated.nbt.annotation.NBT;

import java.util.List;

public class ObjectSample {

    @NBT("boolean")
    public boolean sampleBoolean;

    @NBT("byte")
    public byte sampleByte;

    @NBT("short")
    public short sampleShort;

    @NBT("int")
    public int sampleInt;

    @NBT("long")
    public long sampleLong;

    @NBT("float")
    public float sampleFloat;

    @NBT("double")
    public double sampleDouble;

    @NBT("string")
    public String sampleString;

    public String skipString;

    @NBT("byteArray")
    public byte[] sampleByteArray;

    @NBT("intArray")
    public int[] sampleIntArray;

    @NBT("booleanList")
    public List<Boolean> sampleBooleanList;

    @NBT("byteList")
    public List<Byte> sampleByteList;

    @NBT("shortList")
    public List<Short> sampleShortList;

    @NBT("intList")
    public List<Integer> sampleIntList;

    @NBT("longList")
    public List<Long> sampleLongList;

    @NBT("floatList")
    public List<Float> sampleFloatList;

    @NBT("doubleList")
    public List<Double> sampleDoubleList;

    @NBT("stringList")
    public List<String> sampleStringList;

    @NBT("byteArrayList")
    public List<byte[]> sampleByteArrayList;

    @NBT("intArrayList")
    public List<int[]> sampleIntArrayList;

}
