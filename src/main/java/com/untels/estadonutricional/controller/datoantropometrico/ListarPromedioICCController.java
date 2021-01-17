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
@RequestMapping("/api/datos-antropometricos/icc-grupal")
@CrossOrigin
public class ListarPromedioICCController {

    @Autowired
    private DatoAntropometricoService datoAntropometricoService;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','MEDICO')")
    @GetMapping
    public ResponseEntity<?> listarPromedioICCGrupal() {
        return new ResponseEntity<>(
                new Respuesta<>(
                        datoAntropometricoService.listarPromedioICCGrupal(),
                        "Listado de icc grupal"
                ),
                HttpStatus.OK
        );
    }
}
