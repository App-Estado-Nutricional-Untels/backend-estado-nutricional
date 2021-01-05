
package com.untels.estadonutricional.entity;

import java.util.GregorianCalendar;
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
@Table(name = "recomendacion")
public class Recomendacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(
        name = "mensaje",
        unique = false,
        nullable = false 
    )
    private String mensaje;

    @Column(
        name = "fecha_registro",
        unique = false,
        nullable = false 
    )
    private GregorianCalendar fechaRegistro;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "dato_antropometrico_id",
            referencedColumnName = "id"
    )
    private DatoAntropometrico datoAntropometrico;

    public Recomendacion() {
    }

    public Recomendacion(String mensaje, GregorianCalendar fechaRegistro, DatoAntropometrico datoAntropometrico) {
        this.mensaje = mensaje;
        this.fechaRegistro = fechaRegistro;
        this.datoAntropometrico = datoAntropometrico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public GregorianCalendar getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(GregorianCalendar fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public DatoAntropometrico getDatoAntropometrico() {
        return datoAntropometrico;
    }

    public void setDatoAntropometrico(DatoAntropometrico datoAntropometrico) {
        this.datoAntropometrico = datoAntropometrico;
    }
 
    
}
