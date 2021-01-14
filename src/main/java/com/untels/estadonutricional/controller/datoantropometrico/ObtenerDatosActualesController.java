
package com.untels.estadonutricional.controller.datoantropometrico;

import com.untels.estadonutricional.dto.response.Error;
import com.untels.estadonutricional.dto.response.Respuesta;
import  com.untels.estadonutricional.dto.response.RespuestaError;
import com.untels.estadonutricional.entity.Alumno;
import com.untels.estadonutricional.entity.DatoAntropometrico;
import com.untels.estadonutricional.security.service.DatosAntropometricosService;
import com.untels.estadonutricional.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alumnos/datos-antropometricos")
@CrossOrigin
@Validated
public class ObtenerDatosActualesController {
    
    @Autowired
    private DatosAntropometricosService datoAntropometricoService;
    
    @Autowired
    private AlumnoService alumnoService;
    
    @PreAuthorize("hasAnyRole('ALUMNO','MEDICO','ADMINISTRADOR')")
    @GetMapping("/actual/{id}")
    public ResponseEntity<?> obtenerDatosActuales(
            @PathVariable("id") int id
    ){
        
        if(!alumnoService.existeAlumnoPorId(id)){
            return new ResponseEntity(
                    new RespuestaError(new Error("id","No existe alumno registrado con el id ingresado")),
                    HttpStatus.BAD_REQUEST);
        }
        
        Alumno alumno = alumnoService.obtenerUnoPorId(id);
        
        if(!datoAntropometricoService.existeRegistrosPorAlumno(alumno)){
            return new ResponseEntity(
                    new RespuestaError(new Error("datos antropometricos","No existe datos antropometricos registrados por el alumno")),
                    HttpStatus.BAD_REQUEST);
        }
        
        DatoAntropometrico datosAntropometricos = datoAntropometricoService.obtenerUltimoPorAlumnoId(id).get();
        
        return new ResponseEntity<>(
                new Respuesta<>(
                        datosAntropometricos,
                        "Ultimos datos antropometricos registrados"
                ),
                HttpStatus.OK
        );
    }
    
}
