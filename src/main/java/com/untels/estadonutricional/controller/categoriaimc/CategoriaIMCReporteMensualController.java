package com.untels.estadonutricional.controller.categoriaimc;

import com.untels.estadonutricional.dto.param.CategoriaIMCReporteMensualParam;
import com.untels.estadonutricional.dto.response.Error;
import com.untels.estadonutricional.dto.response.Respuesta;
import com.untels.estadonutricional.dto.response.RespuestaError;
import com.untels.estadonutricional.enums.CategoriaIMC;
import com.untels.estadonutricional.service.CategoriaIMCReporteMensualService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alumnos/imc/reporte")
@CrossOrigin
@Validated
public class CategoriaIMCReporteMensualController {

    @Autowired
    CategoriaIMCReporteMensualService reporteMensualService;

    @PreAuthorize("hasAnyRole('ALUMNO','ADMINISTRADOR','MEDICO')")
    @GetMapping
    public ResponseEntity<?> listar(
            @Valid CategoriaIMCReporteMensualParam params
    ) {

        if (!reporteMensualService.existeReporte(
                CategoriaIMC.valueOf(params.getCategoriaIMC()),
                params.getMes(),
                params.getAnio())) {
            return new ResponseEntity<>(
                    new RespuestaError(new Error(
                            "cualquiera",
                            "No existen registros disponibles"
                    )),
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                new Respuesta<>(
                        reporteMensualService.generarReporte(
                                CategoriaIMC.valueOf(params.getCategoriaIMC()),
                                params.getMes(),
                                params.getAnio()),
                        "Reporte por grupo IMC mes y año"
                ),
                HttpStatus.OK
        );
    }
}
