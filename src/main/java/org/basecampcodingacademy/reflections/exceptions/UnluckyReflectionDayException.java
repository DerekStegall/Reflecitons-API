package org.basecampcodingacademy.reflections.exceptions;

import java.time.LocalDate;

public class UnluckyReflectionDayException extends Exception {
    public LocalDate date;

    public UnluckyReflectionDayException(LocalDate date){
        this.date = date;
    }
}
