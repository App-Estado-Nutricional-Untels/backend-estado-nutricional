
package com.untels.estadonutricional.dto.response;

public class EvolucionIMC {
    
    private float valorIMC;
    private String fechaRegistro;

    public EvolucionIMC(float valorIMC, String fechaRegistro) {
        this.valorIMC = valorIMC;
        this.fechaRegistro = fechaRegistro;
    }

    public float getValorIMC() {
        return valorIMC;
    }

    public void setValorIMC(float valorIMC) {
        this.valorIMC = valorIMC;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
}
