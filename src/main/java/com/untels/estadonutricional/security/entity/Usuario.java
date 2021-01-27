package com.untels.estadonutricional.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.untels.estadonutricional.entity.Persona;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(
            name = "correo_electronico",
            nullable = false,
            unique = true
    )
    private String correoElectronico;

    @Column(
            name = "clave",
            nullable = false
    )
    private String clave;

    @ManyToOne(
            cascade = {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.PERSIST,
                CascadeType.REFRESH
            }
    )
    @JoinColumn(
            name = "rol_id",
            referencedColumnName = "id"
    )
    private Rol rol;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "persona_id",
            referencedColumnName = "id"
    )
    @JsonIgnore
    private Persona persona;

    public Usuario() {
    }

    public Usuario(String correoElectronico, String clave, Rol rol, Persona persona) {
        this.correoElectronico = correoElectronico;
        this.clave = clave;
        this.rol = rol;
        this.persona = persona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
