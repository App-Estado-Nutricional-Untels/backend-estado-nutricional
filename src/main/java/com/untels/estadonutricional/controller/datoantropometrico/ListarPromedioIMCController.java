package com.untels.estadonutricional.controller.datoantropometrico;

import com.untels.estadonutricional.dto.response.Respuesta;
import com.untels.estadonutricional.service.DatoAntropometricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/datos-antropometricos/imc-grupal")
@CrossOrigin
public class ListarPromedioIMCController {

    @Autowired
    private DatoAntropometricoService datoAntropometricoService;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','MEDICO')")
    @GetMapping
    public ResponseEntity<?> listarPromedioIMCGrupal() {
        return new ResponseEntity<>(
                new Respuesta<>(
                        datoAntropometricoService.listarPromedioIMCGrupal(),
                        "Listado de imc grupal"
                ),
                HttpStatus.OK
        );
    }
}
