package com.untels.estadonutricional.utils;

public abstract class RegExpPatterns {

    public static final String FECHA
            = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";

    public static final String CLAVE = "[a-zA-Z0-9]*";
}
