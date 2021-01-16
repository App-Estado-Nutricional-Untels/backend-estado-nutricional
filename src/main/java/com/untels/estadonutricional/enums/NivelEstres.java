package com.untels.estadonutricional.enums;

public enum NivelEstres {
    LEVE, MODERADO, SEVERO;

    public static NivelEstres fromString(String nivelEstres) {
        NivelEstres nivelEstresEnum = null;
        switch (nivelEstres.toUpperCase()) {
            case "LEVE":
                nivelEstresEnum = LEVE;
                break;
            case "MODERADO":
                nivelEstresEnum = MODERADO;
                break;
            case "SEVERO":
                nivelEstresEnum = SEVERO;
                break;
        }
        return nivelEstresEnum;
    }
}
