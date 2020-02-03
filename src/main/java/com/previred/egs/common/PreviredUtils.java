package com.previred.egs.common;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PreviredUtils {

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return   Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
}
