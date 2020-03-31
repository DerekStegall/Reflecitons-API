
package org.basecampcodingacademy.reflections.exceptions;

import java.time.LocalDate;

public class ReflectionAlreadyExists extends Exception {
    public LocalDate date;
    public ReflectionAlreadyExists(LocalDate date) {
        this.date = date;
    }
}