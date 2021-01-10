package com.untels.estadonutricional.security.dto.request;

import javax.validation.constraints.NotBlank;

public class ActualizarUsuarioBody {

    @NotBlank
    private String nombres;

    @NotBlank
    private String apePaterno;

    @NotBlank
    private String apeMaterno;

    public ActualizarUsuarioBody(
            String nombres,
            String apePaterno,
            String apeMaterno
    ) {
        this.nombres = nombres;
        this.apePaterno = apePaterno;
        this.apeMaterno = apeMaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApePaterno() {
        return apePaterno;
    }

    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

}
