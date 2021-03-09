package ru.itis.xokken.exception;

public class ConvertToOtherObjectException extends Exception {
    public ConvertToOtherObjectException(ReflectiveOperationException e) {
        super(e);
    }
}
