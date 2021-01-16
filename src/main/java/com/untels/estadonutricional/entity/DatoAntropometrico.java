package com.untels.estadonutricional.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.untels.estadonutricional.enums.CategoriaICC;
import com.untels.estadonutricional.enums.CategoriaIMC;
import com.untels.estadonutricional.enums.NivelEstres;
import java.util.GregorianCalendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dato_antropometrico")
public class DatoAntropometrico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(
            name = "estatura",
            nullable = false
    )
    private float estatura;

    @Column(
            name = "peso",
            nullable = false
    )
    private float peso;

    @Column(
            name = "contorno_cintura",
            nullable = false
    )
    private float contornoCintura;

    @Column(
            name = "contorno_cadera",
            nullable = false
    )
    private float contornoCadera;

    @Column(
            name = "mivel_estres",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private NivelEstres nivelEstres;

    @Column(
            name = "actividad_fisica",
            nullable = false
    )
    private float actividadFisica;

    @Column(
            name = "rendimiento_academico",
            nullable = false
    )
    private String rendimientoAcademico;

    @Column(
            name = "fecha_registro",
            nullable = false
    )
    private GregorianCalendar fechaRegistro;

    @Column(
            name = "valor_imc",
            nullable = false
    )
    private float valorIMC;

    @Column(
            name = "valor_icc",
            nullable = false
    )
    private float valorICC;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "alumno_id",
            referencedColumnName = "id"
    )
    @JsonIgnore
    private Alumno alumno;

    @Column(
            name = "categoria_imc",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private CategoriaIMC categoriaimc;

    @Column(
            name = "categoria_icc",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private CategoriaICC categoriaicc;

    @OneToOne(mappedBy = "datoAntropometrico")
    private Recomendacion recomendacion;

    public DatoAntropometrico() {
    }

    public DatoAntropometrico(
            float estatura,
            float peso,
            float contornoCintura,
            float contornoCadera,
            NivelEstres nivelEstres,
            float actividadFisica,
            String rendimientoAcademico,
            GregorianCalendar fechaRegistro,
            Alumno alumno
    ) {
        this.estatura = estatura;
        this.peso = peso;
        this.contornoCintura = contornoCintura;
        this.contornoCadera = contornoCadera;
        this.nivelEstres = nivelEstres;
        this.actividadFisica = actividadFisica;
        this.rendimientoAcademico = rendimientoAcademico;
        this.fechaRegistro = fechaRegistro;
        this.alumno = alumno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public NivelEstres getNivelEstres() {
        return nivelEstres;
    }

    public void setNivelEstres(NivelEstres nivelEstres) {
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

    public GregorianCalendar getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(GregorianCalendar fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Recomendacion getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(Recomendacion recomendacion) {
        this.recomendacion = recomendacion;
    }

    public float getValorIMC() {
        return valorIMC;
    }

    public void setValorIMC(float valorIMC) {
        this.valorIMC = valorIMC;
    }

    public float getValorICC() {
        return valorICC;
    }

    public void setValorICC(float valorICC) {
        this.valorICC = valorICC;
    }

    public CategoriaIMC getCategoriaimc() {
        return categoriaimc;
    }

    public void setCategoriaimc(CategoriaIMC categoriaimc) {
        this.categoriaimc = categoriaimc;
    }

    public CategoriaICC getCategoriaicc() {
        return categoriaicc;
    }

    public void setCategoriaicc(CategoriaICC categoriaicc) {
        this.categoriaicc = categoriaicc;
    }

}
