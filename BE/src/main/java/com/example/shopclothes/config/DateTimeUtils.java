package com.example.shopclothes.config;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
public class DateTimeUtils {
    public static Date convertToUTC(Date date) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(localDateTime.atZone(ZoneId.of("UTC")).toInstant());
    }

    public static Date convertToLocalTime(Date date) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
