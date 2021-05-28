package com.example.payments_gateway1.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeService {
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public java.util.Date parseDate(String date) {
        try {
            return DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public java.util.Date parseTimestamp(String timestamp) {
        try {
            return DATE_TIME_FORMAT.parse(timestamp);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
