package com.untels.estadonutricional.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(
            name = "codigo_medico",
            unique = true,
            nullable = false,
            length = 50
    )
    private String codigoMedico;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "persona_id",
            referencedColumnName = "id"
    )
    private Persona persona;

    public Medico() {
    }

    public Medico(String codigoMedico, Persona persona) {
        this.codigoMedico = codigoMedico;
        this.persona = persona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(String codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "Medico{"
                + "id=" + id
                + ", codigoMedico=" + codigoMedico
                + ", persona=" + persona
                + '}';
    }

}
