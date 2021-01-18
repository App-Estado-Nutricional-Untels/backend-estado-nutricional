package com.untels.estadonutricional.dto.param;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CategoriaIMCReporteMensualParam {

    @NotNull
    @Min(1)
    @Max(12)
    private Integer mes;

    @NotNull
    @Min(2000)
    private Integer anio;

    @NotBlank
    @Pattern(
            regexp = "DELGADEZ_GRADO_III|"
            + "DELGADEZ_GRADO_II|"
            + "DELGADEZ_GRADO_I|"
            + "NORMAL|"
            + "SOBREPESO|"
            + "OBESIDAD_GRADO_I|"
            + "OBESIDAD_GRADO_II|"
            + "OBESIDAD_GRADO_III",
            flags = Pattern.Flag.CASE_INSENSITIVE
    )
    private String categoriaIMC;

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getCategoriaIMC() {
        return categoriaIMC;
    }

    public void setCategoriaIMC(String categoriaIMC) {
        this.categoriaIMC = categoriaIMC;
    }

}
