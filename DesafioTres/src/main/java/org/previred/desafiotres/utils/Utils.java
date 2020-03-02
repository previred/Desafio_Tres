package org.previred.desafiotres.utils;

import com.previred.desafio.tres.uf.vo.Uf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class Utils {
    private Utils() {

    }

    public static long difer(Date date1, Date date2) {
        return TimeUnit.DAYS.convert(
                date2.getTime() - date1.getTime(),
                TimeUnit.MILLISECONDS);
    }

    public static Date sumDays(Date date1, long days) {
        return localToDate(dateToLocal(date1).plusDays(days));
    }

    public static Date resDays(Date date1, long days) {
        return localToDate(dateToLocal(date1).minusDays(days));
    }

    public static Date localToDate(LocalDateTime l) {
        return Date.from(l.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime dateToLocal(Date d) {
        return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static boolean containDate(List<Uf> l, Date d) {
        return null != l.stream().filter(
                uf -> uf.getFecha().compareTo(d) == 0).findAny()
                .orElse(null);
    }

    public static void saveToFile(String content, String fileName) throws IOException {
        Files.write(Paths.get(fileName), content.getBytes());
    }

}
