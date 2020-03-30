package com.ricardoc333.desafio.tres;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

//package com.previred.desafio.tres.uf.tools;

import java.time.LocalDate;
import java.util.Random;

public class RandomDate {
    private final LocalDate minDate;
    private final LocalDate maxDate;
    private final Random random;

    public RandomDate(LocalDate minDate, LocalDate maxDate) {
        this.minDate = minDate;
        this.maxDate = maxDate;
        this.random = new Random();
    }

    public LocalDate nextDate() {
        int minDay = (int)this.minDate.toEpochDay();
        int maxDay = (int)this.maxDate.toEpochDay();
        long randomDay = (long)(minDay + this.random.nextInt(maxDay - minDay));
        return LocalDate.ofEpochDay(randomDay);
    }

    public String toString() {
        return "RandomDate{maxDate=" + this.maxDate + ", minDate=" + this.minDate + '}';
    }
}
