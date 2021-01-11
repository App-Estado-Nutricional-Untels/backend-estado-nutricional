package com.untels.estadonutricional.controller.alumno;

import com.untels.estadonutricional.dto.response.PorcentajeSexoResponse;
import com.untels.estadonutricional.dto.response.Respuesta;
import com.untels.estadonutricional.service.AlumnoService;
import com.untels.estadonutricional.service.PorcentajeSexoAlumnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alumnos")
@CrossOrigin
public class PorcentajeSexoController {

    @Autowired
    PorcentajeSexoAlumnosService porcentajeSexoAlumnosService;

    @Autowired
    AlumnoService alumnoService;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','MEDICO')")
    @GetMapping("/porcentaje-sexo")
    public ResponseEntity<?> obtenerProcentajeSexoAlumnosGrupal() {

        PorcentajeSexoResponse porcentajeSexoResponse
                = new PorcentajeSexoResponse(
                        porcentajeSexoAlumnosService
                                .obtenerPorcentajeSexoFemenino(),
                        porcentajeSexoAlumnosService
                                .obtenerPorcentajeSexoMasculino(),
                        alumnoService.totalAlumnosFemeninos(),
                        alumnoService.totalAlumnosMasculinos(),
                        alumnoService.totalAlumnos()
                );

        return new ResponseEntity<>(
                new Respuesta<>(
                        porcentajeSexoResponse,
                        "Porcentajes de sexos de alumnos"),
                HttpStatus.OK);
    }
}
