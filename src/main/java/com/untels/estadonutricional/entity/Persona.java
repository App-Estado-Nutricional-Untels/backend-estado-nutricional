package com.untels.estadonutricional.entity;

import com.untels.estadonutricional.enums.SexoNombre;
import com.untels.estadonutricional.security.entity.Usuario;
import java.util.GregorianCalendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(
            name = "nombre",
            nullable = false
    )
    private String nombre;

    @Column(
            name = "ape_paterno",
            nullable = false
    )
    private String apepaterno;

    @Column(
            name = "ape_materno",
            nullable = false
    )
    private String apematerno;

    @Column(
            name = "sexo",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private SexoNombre sexo;

    @Column(
            name = "dni",
            unique = true,
            nullable = false
    )
    private String dni;

    @Column(
            name = "fecha_nacimiento",
            nullable = true
    )
    private GregorianCalendar fechaNacimiento;

    @Formula("YEAR(now()) - YEAR(fecha_nacimiento) - ( DAYOFYEAR(now()) < DAYOFYEAR(fecha_nacimiento) )")
    private Integer edad;

    @OneToOne(mappedBy = "persona")
    private Usuario usuario;

    public Persona() {
    }

    public Persona(
            String nombre,
            String apepaterno,
            String apematerno,
            SexoNombre sexo,
            String dni,
            GregorianCalendar fechaNacimiento,
            Usuario usuario
    ) {
        this.nombre = nombre;
        this.apepaterno = apepaterno;
        this.apematerno = apematerno;
        this.sexo = sexo;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApepaterno() {
        return apepaterno;
    }

    public void setApepaterno(String apepaterno) {
        this.apepaterno = apepaterno;
    }

    public String getApematerno() {
        return apematerno;
    }

    public void setApematerno(String apematerno) {
        this.apematerno = apematerno;
    }

    public SexoNombre getSexo() {
        return sexo;
    }

    public void setSexo(SexoNombre sexo) {
        this.sexo = sexo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public GregorianCalendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona{"
                + "id=" + id
                + ", nombre=" + nombre
                + ", apepaterno=" + apepaterno
                + ", apematerno=" + apematerno
                + ", sexo=" + sexo
                + ", dni=" + dni
                + ", fechaNacimiento=" + fechaNacimiento
                + '}';
    }

}
