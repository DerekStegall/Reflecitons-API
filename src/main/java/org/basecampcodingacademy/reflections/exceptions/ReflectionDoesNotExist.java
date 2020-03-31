package org.basecampcodingacademy.reflections.exceptions;

public class ReflectionDoesNotExist extends Throwable {
    public Integer reflectionId;
    public ReflectionDoesNotExist(Integer reflectionId) {
        this.reflectionId = reflectionId;
    }
}
