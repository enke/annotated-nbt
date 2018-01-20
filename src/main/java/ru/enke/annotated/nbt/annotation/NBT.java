package ru.enke.annotated.nbt.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(value = FIELD)
@Retention(RUNTIME)
public @interface NBT {
    String value() default "";
}
