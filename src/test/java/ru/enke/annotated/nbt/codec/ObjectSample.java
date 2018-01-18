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

    @NBT("list")
    public List<?> sampleList;

}
