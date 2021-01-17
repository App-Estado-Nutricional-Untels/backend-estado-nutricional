
package com.untels.estadonutricional.dto.response;

public class EvolucionICC {
    
    private float valorICC;
    private String fechaRegistro;

    public EvolucionICC(float valorICC, String fechaRegistro) {
        this.valorICC = valorICC;
        this.fechaRegistro = fechaRegistro;
    }

    public EvolucionICC() {
    }

    public float getValorICC() {
        return valorICC;
    }

    public void setValorICC(float valorICC) {
        this.valorICC = valorICC;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
}
