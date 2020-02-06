package br.com.projeto.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author jefferson
 */
public final class DateUtil {

    private static final String PATTERN_LOCAL_DATE = "dd/MM/yyyy";

    private static final String PATTERN_LOCAL_TIME = "HH:mm:ss";

    /**
     * Local Date string to LocalDate by Pattern : day/month/year
     *
     * @param localDateString local date in format string
     * @return LocalDate
     */
    public static LocalDate rawStringToLocalDate(final String localDateString) {
        return LocalDate.parse(localDateString, DateTimeFormatter.ofPattern(PATTERN_LOCAL_DATE));
    }

    /**
     * Local Time string to LocalTime by Pattern : hour:minute:seconds
     *
     * @param localTimeString time in format string
     * @return LocalTime
     */
    public static LocalTime rawStringToLocalTime(final String localTimeString) {
        return LocalTime.parse(localTimeString, DateTimeFormatter.ofPattern(PATTERN_LOCAL_TIME));
    }

    /**
     * Private Constructor because this class is a object class
     */
    private DateUtil() {
    }

}
