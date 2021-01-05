
package com.untels.estadonutricional.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "codigo_medico_valido")
public class CodigoMedValido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(
        name = "codigo_medico",
        nullable = false,
        length = 50
    )
    private String codigoMedico;

    public CodigoMedValido() {
    }

    public CodigoMedValido(String codigoMedico) {
        this.codigoMedico = codigoMedico;
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

    @Override
    public String toString() {
        return "CodigoMedValido{" + "id=" + id + ", codigoMedico=" + codigoMedico + '}';
    }
    
}
