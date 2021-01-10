package com.untels.estadonutricional.dto.param;

public class ListarAlumnosParam {

    private String nombres;
    private String apePaterno;
    private String apeMaterno;
    private String dni;
    private String codigoUniversitario;
    private Integer edadMin;
    private Integer edadMax;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApePaterno() {
        return apePaterno;
    }

    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCodigoUniversitario() {
        return codigoUniversitario;
    }

    public void setCodigoUniversitario(String codigoUniversitario) {
        this.codigoUniversitario = codigoUniversitario;
    }

    public Integer getEdadMin() {
        return edadMin;
    }

    public void setEdadMin(Integer edadMin) {
        this.edadMin = edadMin;
    }

    public Integer getEdadMax() {
        return edadMax;
    }

    public void setEdadMax(Integer edadMax) {
        this.edadMax = edadMax;
    }

}
