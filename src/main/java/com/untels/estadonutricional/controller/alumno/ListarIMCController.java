
package com.untels.estadonutricional.controller.alumno;

import com.untels.estadonutricional.dto.response.Error;
import com.untels.estadonutricional.dto.response.EvolucionIMC;
import com.untels.estadonutricional.dto.response.Respuesta;
import com.untels.estadonutricional.dto.response.RespuestaError;
import com.untels.estadonutricional.entity.Alumno;
import com.untels.estadonutricional.service.AlumnoService;
import com.untels.estadonutricional.service.DatoAntropometricoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alumnos")
@CrossOrigin
public class ListarIMCController {
    
    @Autowired
    AlumnoService alumnoService;
            
    @Autowired
    DatoAntropometricoService datoAntropometricoService;
    
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','MEDICO','ALUMNO')")
    @GetMapping("/imc-evolucion/{id}")
    public ResponseEntity<?> listarRegistrosIMC(
            @PathVariable("id") int id
    ){
        
        if(!alumnoService.existeAlumnoPorId(id)){
            return new ResponseEntity(
                    new RespuestaError(new Error("id","No existe alumno registrado con el id ingresado")),
                    HttpStatus.BAD_REQUEST);
        }
        
        Alumno alumno = alumnoService.obtenerUnoPorId(id);
        System.out.println(alumno);
        
        if(!datoAntropometricoService.existeRegistrosPorAlumno(alumno)){
            return new ResponseEntity(
                    new RespuestaError(new Error("datos antropometricos","No existe datos antropometricos registrados por el alumno")),
                    HttpStatus.BAD_REQUEST);
        }
        
        List<EvolucionIMC> evolucionIMC = datoAntropometricoService.listarEvolucionIMCPorAlumnoId(id);
        
        return new ResponseEntity<>(
                new Respuesta<>(
                        evolucionIMC,
                        "Listado de imc"
                ),
                HttpStatus.OK
        );
    }
    
}
