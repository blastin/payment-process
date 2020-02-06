package br.com.projeto.utils;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateUtilTest {

    @Test
    public void whenLocalDateIsConstructedByRawString() {
        final String localDate = "05/02/2020";
        Assert.assertEquals("Should return object LocalDate equals", LocalDate.of(2020, 2, 5), DateUtil.localDateStringToLocalDate(localDate));
    }

    @Test
    public void whenLocalTimeisConstructedByRawString() {
        final String localTime = "10:20:00";
        Assert.assertEquals("Should return object LocalTime equals", LocalTime.of(10, 20, 0), DateUtil.localTimeStringToLocalDate(localTime));
    }
}
