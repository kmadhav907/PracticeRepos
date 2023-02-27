package com.project.poc.utilities;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class GlobalUtils {
    public static final Long NUM_OF_DAYS_AFTER_DELETION = 90L;
    public static  final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static final SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    public static String formatDateAndTime(LocalDateTime dateAndTime){
           return dateAndTime.format(format);
    }
    public static Boolean isRecordOldThan90days(String dateAndTimeAtDeleted) throws ParseException {
        Date dateOfDeleted = parser.parse(dateAndTimeAtDeleted);
        Date currentDate = parser.parse(formatDateAndTime(LocalDateTime.now()));
        Long timeDifference = (Long) (currentDate.getTime() - dateOfDeleted.getTime());
        Long daysDifference = timeDifference / ((1000 * 60 * 60 * 24 ) % 365);
        return  daysDifference > NUM_OF_DAYS_AFTER_DELETION;
    }
}
