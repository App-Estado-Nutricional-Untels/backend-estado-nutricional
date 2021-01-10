package com.untels.estadonutricional.security.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class IniciarSesionBody {

    @NotBlank
    @Email
    private String correoElectronico;

    @NotBlank
    private String clave;

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

}
