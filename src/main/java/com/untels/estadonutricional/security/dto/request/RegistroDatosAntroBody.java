package com.untels.estadonutricional.security.dto.request;

import com.untels.estadonutricional.utils.RegExpPatterns;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RegistroDatosAntroBody {

    @Min(0)
    private float estatura;

    @Min(0)
    private float peso;

    @Min(0)
    private float contornoCintura;

    @Min(0)
    private float contornoCadera;

    @NotBlank
    @Pattern(
            regexp = "LEVE|MODERADO|SEVERO",
            flags = Pattern.Flag.CASE_INSENSITIVE
    )
    private String nivelEstres;

    @Min(0)
    private float actividadFisica;

    @NotBlank
    private String rendimientoAcademico;

    @NotBlank
    @Pattern(
            regexp = RegExpPatterns.FECHA,
            message = "fecha incorrecta"
    )
    private String fechaNacimiento;

    public float getEstatura() {
        return estatura;
    }

    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getContornoCintura() {
        return contornoCintura;
    }

    public void setContornoCintura(float contornoCintura) {
        this.contornoCintura = contornoCintura;
    }

    public float getContornoCadera() {
        return contornoCadera;
    }

    public void setContornoCadera(float contornoCadera) {
        this.contornoCadera = contornoCadera;
    }

    public String getNivelEstres() {
        return nivelEstres;
    }

    public void setNivelEstres(String nivelEstres) {
        this.nivelEstres = nivelEstres;
    }

    public float getActividadFisica() {
        return actividadFisica;
    }

    public void setActividadFisica(float actividadFisica) {
        this.actividadFisica = actividadFisica;
    }

    public String getRendimientoAcademico() {
        return rendimientoAcademico;
    }

    public void setRendimientoAcademico(String rendimientoAcademico) {
        this.rendimientoAcademico = rendimientoAcademico;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}
