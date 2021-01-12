package com.untels.estadonutricional.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RegistroRecomendacionBody {

    @Min(0)
    private Integer datoAntropometricoId;

    @NotBlank
    private String mensaje;

    public Integer getDatoAntropometricoId() {
        return datoAntropometricoId;
    }

    public void setDatoAntropometricoId(Integer datoAntropometricoId) {
        this.datoAntropometricoId = datoAntropometricoId;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
