package com.untels.estadonutricional.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class AgeCalculator {

    private Date birthDate;

    public AgeCalculator(Date birthDate) {
        this.birthDate = birthDate;
    }

    public AgeCalculator(GregorianCalendar calendar) {
        this.birthDate = calendar.getTime();
    }

    public int age() {
        Date date = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(birthDate));
        int d2 = Integer.parseInt(formatter.format(date));
        int age = (d2 - d1) / 10000;
        return age;
    }
}
