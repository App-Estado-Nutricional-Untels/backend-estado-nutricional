
package com.untels.estadonutricional.dto.request;

import com.untels.estadonutricional.utils.RegExpPatterns;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistroAlumnoBody {
    
    @NotBlank
    private String nombres;

    @NotBlank
    private String apePaterno;

    @NotBlank
    private String apeMaterno;

    @NotBlank
    @Email
    private String correoElectronico;

    @NotBlank
    @Size(min = 8, max = 8)
    private String dni;

    @NotBlank
    @Pattern(
            regexp = "F|M",
            flags = Pattern.Flag.CASE_INSENSITIVE
    )
    private String sexo;

    @Size(min = 10)
    private String codigo;

    @NotBlank
    @Size(min = 6)
    @Pattern(
            regexp = RegExpPatterns.CLAVE,
            message = "clave incorrecta"
    )
    private String clave;

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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
