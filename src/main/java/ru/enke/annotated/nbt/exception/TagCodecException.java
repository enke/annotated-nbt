package ru.enke.annotated.nbt.exception;

public class TagCodecException extends Exception {

    public TagCodecException(final String message) {
        super(message);
    }

    public TagCodecException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
