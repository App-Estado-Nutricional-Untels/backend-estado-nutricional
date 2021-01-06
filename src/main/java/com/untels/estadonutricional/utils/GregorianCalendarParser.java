package com.untels.estadonutricional.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GregorianCalendarParser {

    public static GregorianCalendar parse(String fecha) {
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/YYYY");
        Date date = null;
        try {
            date = format.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(GregorianCalendarParser.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);

        return cal;
    }
}
