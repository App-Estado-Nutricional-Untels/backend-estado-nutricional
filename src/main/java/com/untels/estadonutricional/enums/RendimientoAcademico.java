package com.untels.estadonutricional.enums;

public enum RendimientoAcademico {
    PESIMO, MALO, NORMAL, BUENO, EXCELENTE;

    public static RendimientoAcademico fromString(String rendimiento) {
        RendimientoAcademico rendimientonum = null;
        switch (rendimiento.toUpperCase()) {
            case "PESIMO":
                rendimientonum = PESIMO;
                break;
            case "MALO":
                rendimientonum = MALO;
                break;
            case "NORMAL":
                rendimientonum = NORMAL;
                break;
            case "BUENO":
                rendimientonum = BUENO;
                break;
            case "EXCELENTE":
                rendimientonum = EXCELENTE;
                break;
        }
        return rendimientonum;
    }
}
