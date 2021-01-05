package com.untels.estadonutricional.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "codigo_uni_valido")
public class CodigoUniValido {
    
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

    public CodigoUniValido() {
    }

    public CodigoUniValido(String codigoUniversitario) {
        this.codigoUniversitario = codigoUniversitario;
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

    @Override
    public String toString() {
        return "CodigoUniValido{" + "id=" + id + ", codigoUniversitario=" + codigoUniversitario + '}';
    }
    
}
