package com.untels.estadonutricional.security.dto.response;

import java.util.Map;

public class UsuarioAutenticadoResponse {

    private boolean ok = true;
    private String token;
    private String tipoToken;
    private Map<String, Object> datos;

    public UsuarioAutenticadoResponse(
            String token,
            String tipoToken,
            Map<String, Object> datos
    ) {
        this.token = token;
        this.tipoToken = tipoToken;
        this.datos = datos;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipoToken() {
        return tipoToken;
    }

    public void setTipoToken(String tipoToken) {
        this.tipoToken = tipoToken;
    }

    public Map<String, Object> getDatos() {
        return datos;
    }

    public void setDatos(Map<String, Object> datos) {
        this.datos = datos;
    }

}
