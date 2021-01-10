package com.untels.estadonutricional.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(
            name = "codigo_universitario",
            unique = true,
            nullable = false,
            length = 10
    )
    private String codigoUniversitario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "persona_id",
            referencedColumnName = "id"
    )
    private Persona persona;

    @OneToMany(mappedBy = "alumno")
    private Set<DatoAntropometrico> datosAntropometricos = new HashSet<>();

    public Alumno() {
    }

    public Alumno(
            String codigoUniversitario,
            Persona persona
    ) {
        this.codigoUniversitario = codigoUniversitario;
        this.persona = persona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoUniversitario() {
        return codigoUniversitario;
    }

    public void setCodigoUniversitario(String codigoUniversitario) {
        this.codigoUniversitario = codigoUniversitario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Set<DatoAntropometrico> getDatosAntropometricos() {
        return datosAntropometricos;
    }

    public void setDatosAntropometricos(Set<DatoAntropometrico> datosAntropometricos) {
        this.datosAntropometricos = datosAntropometricos;
    }

    @Override
    public String toString() {
        return "Alumno{"
                + "id=" + id
                + ", codigoUniversitario=" + codigoUniversitario
                + ", persona=" + persona
                + ", datosAntropometricos=" + datosAntropometricos
                + '}';
    }

}
