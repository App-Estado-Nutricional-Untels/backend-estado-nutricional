package com.untels.estadonutricional.controller.alumno;

import com.untels.estadonutricional.dto.response.EvolucionICC;
import com.untels.estadonutricional.dto.response.Respuesta;
import com.untels.estadonutricional.dto.response.RespuestaError;
import com.untels.estadonutricional.entity.Alumno;
import com.untels.estadonutricional.entity.Persona;
import com.untels.estadonutricional.service.AlumnoService;
import com.untels.estadonutricional.service.DatoAntropometricoService;
import com.untels.estadonutricional.service.PersonaService;
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
@RequestMapping("/api/personas")
@CrossOrigin
public class ListarICCPorPersonaController {

    @Autowired
    AlumnoService alumnoService;

    @Autowired
    DatoAntropometricoService datoAntropometricoService;

    @Autowired
    PersonaService personaService;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','MEDICO','ALUMNO')")
    @GetMapping("/icc-evolucion/{id}")
    public ResponseEntity<?> listarRegistrosIMCPorPersona(
            @PathVariable("id") int id
    ) {

        if (!personaService.existePersonaPorId(id)) {
            return new ResponseEntity(
                    new RespuestaError(new com.untels.estadonutricional.dto.response.Error("id", "No existe persona registrado con el id ingresado")),
                    HttpStatus.BAD_REQUEST);
        }

        Persona persona = personaService.obtenerPorId(id);

        if (!alumnoService.existePorPersona(persona)) {
            return new ResponseEntity(
                    new RespuestaError(new com.untels.estadonutricional.dto.response.Error("alumno", "No existe alumno registrado con la persona ingresada")),
                    HttpStatus.BAD_REQUEST);
        }

        Alumno alumno = alumnoService.obtenerUnoPorPersona(persona);

        if (!datoAntropometricoService.existeRegistrosPorAlumno(alumno)) {
            return new ResponseEntity(
                    new RespuestaError(new com.untels.estadonutricional.dto.response.Error("datos antropometricos", "No existe datos antropometricos registrados por el alumno")),
                    HttpStatus.BAD_REQUEST);
        }

        List<EvolucionICC> evolucionICC
                = datoAntropometricoService.listarEvolucionICCPorAlumnoId(alumno.getId());

        return new ResponseEntity<>(
                new Respuesta<>(
                        evolucionICC,
                        "Listado de icc"
                ),
                HttpStatus.OK
        );
    }

}
