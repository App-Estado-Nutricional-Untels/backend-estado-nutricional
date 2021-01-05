
package com.untels.estadonutricional.entity;

import java.util.GregorianCalendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
        name = "nivel_estres",
        nullable = false 
    )
    private float nivelEstres;

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
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "alumno_id",
            referencedColumnName = "id"
    )
    private Alumno alumno;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "categoria_icc_id",
            referencedColumnName = "id"
    )
    private CategoriaICC categoriaICC;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "categoria_imc_id",
            referencedColumnName = "id"
    )
    private CategoriaIMC categoriaIMC;

    public DatoAntropometrico() {
    }

    public DatoAntropometrico(float estatura, float peso, float contornoCintura, float contornoCadera, float nivelEstres, float actividadFisica, String rendimientoAcademico, GregorianCalendar fechaRegistro, Alumno alumno, CategoriaICC categoriaICC, CategoriaIMC categoriaIMC) {
        this.estatura = estatura;
        this.peso = peso;
        this.contornoCintura = contornoCintura;
        this.contornoCadera = contornoCadera;
        this.nivelEstres = nivelEstres;
        this.actividadFisica = actividadFisica;
        this.rendimientoAcademico = rendimientoAcademico;
        this.fechaRegistro = fechaRegistro;
        this.alumno = alumno;
        this.categoriaICC = categoriaICC;
        this.categoriaIMC = categoriaIMC;
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

    public float getNivelEstres() {
        return nivelEstres;
    }

    public void setNivelEstres(float nivelEstres) {
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

    public CategoriaICC getCategoriaICC() {
        return categoriaICC;
    }

    public void setCategoriaICC(CategoriaICC categoriaICC) {
        this.categoriaICC = categoriaICC;
    }

    public CategoriaIMC getCategoriaIMC() {
        return categoriaIMC;
    }

    public void setCategoriaIMC(CategoriaIMC categoriaIMC) {
        this.categoriaIMC = categoriaIMC;
    }

    @Override
    public String toString() {
        return "DatoAntropometrico{" + "id=" + id + ", estatura=" + estatura + ", peso=" + peso + ", contornoCintura=" + contornoCintura + ", contornoCadera=" + contornoCadera + ", nivelEstres=" + nivelEstres + ", actividadFisica=" + actividadFisica + ", rendimientoAcademico=" + rendimientoAcademico + ", fechaRegistro=" + fechaRegistro + ", alumno=" + alumno + ", categoriaICC=" + categoriaICC + ", categoriaIMC=" + categoriaIMC + '}';
    }
    
}
