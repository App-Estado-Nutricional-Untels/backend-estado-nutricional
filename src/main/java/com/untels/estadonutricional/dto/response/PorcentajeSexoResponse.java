package com.untels.estadonutricional.dto.response;

public class PorcentajeSexoResponse {

    private float porcentajeFemenino;
    private float porcentajeMasculino;
    private long totalAlumnosFemeninos;
    private long totalAlumnosMasculinos;
    private long totalAlumnos;

    public PorcentajeSexoResponse(
            float porcentajeFemenino,
            float porcentajeMasculino,
            long totalAlumnosFemeninos,
            long totalAlumnosMasculinos,
            long totalAlumnos
    ) {
        this.porcentajeFemenino = porcentajeFemenino;
        this.porcentajeMasculino = porcentajeMasculino;
        this.totalAlumnosMasculinos = totalAlumnosMasculinos;
        this.totalAlumnosFemeninos = totalAlumnosFemeninos;
        this.totalAlumnos = totalAlumnos;
    }

    public float getPorcentajeFemenino() {
        return porcentajeFemenino;
    }

    public void setPorcentajeFemenino(float porcentajeFemenino) {
        this.porcentajeFemenino = porcentajeFemenino;
    }

    public float getPorcentajeMasculino() {
        return porcentajeMasculino;
    }

    public void setPorcentajeMasculino(float porcentajeMasculino) {
        this.porcentajeMasculino = porcentajeMasculino;
    }

    public long getTotalAlumnosMasculinos() {
        return totalAlumnosMasculinos;
    }

    public void setTotalAlumnosMasculinos(long totalAlumnosMasculinos) {
        this.totalAlumnosMasculinos = totalAlumnosMasculinos;
    }

    public long getTotalAlumnosFemeninos() {
        return totalAlumnosFemeninos;
    }

    public void setTotalAlumnosFemeninos(long totalAlumnosFemeninos) {
        this.totalAlumnosFemeninos = totalAlumnosFemeninos;
    }

    public long getTotalAlumnos() {
        return totalAlumnos;
    }

    public void setTotalAlumnos(long totalAlumnos) {
        this.totalAlumnos = totalAlumnos;
    }

}
