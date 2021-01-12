package com.untels.estadonutricional.controller.datoantropometrico;

import com.untels.estadonutricional.dto.request.RegistroRecomendacionBody;
import com.untels.estadonutricional.dto.response.Error;
import com.untels.estadonutricional.dto.response.Respuesta;
import com.untels.estadonutricional.dto.response.RespuestaError;
import com.untels.estadonutricional.entity.DatoAntropometrico;
import com.untels.estadonutricional.entity.Recomendacion;
import com.untels.estadonutricional.service.DatoAntropometricoService;
import com.untels.estadonutricional.service.RecomendacionService;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/datos-antropometricos")
@CrossOrigin
@Validated
public class RegistroRecomendacionController {

    @Autowired
    RecomendacionService recomendacionService;

    @Autowired
    DatoAntropometricoService datoAntropometricoService;

    @PreAuthorize("hasRole('MEDICO')")
    @PostMapping("/recomendacion")
    public ResponseEntity<?> registrarRecomendacion(
            @Valid @RequestBody RegistroRecomendacionBody registroRecomendacionBody
    ) {
        List<Error> errores = getErrores(registroRecomendacionBody);

        if (!errores.isEmpty()) {
            return new ResponseEntity(
                    new RespuestaError(errores),
                    HttpStatus.BAD_REQUEST
            );
        }

        DatoAntropometrico datoAntropometrico
                = datoAntropometricoService.obtenerPorId(
                        registroRecomendacionBody
                                .getDatoAntropometricoId())
                        .get();

        Recomendacion recomendacion = new Recomendacion(
                registroRecomendacionBody.getMensaje(),
                new GregorianCalendar(),
                datoAntropometrico
        );

        recomendacionService.guardar(recomendacion);
        datoAntropometrico.setRecomendacion(recomendacion);
        datoAntropometricoService.guardar(datoAntropometrico);

        return new ResponseEntity<>(
                new Respuesta<>(
                        recomendacion,
                        "Recomendación registrada"
                ),
                HttpStatus.CREATED
        );
    }

    private List<Error> getErrores(
            RegistroRecomendacionBody registroRecomendacionBody
    ) {
        List<Error> errores = new ArrayList<>();

        if (!datoAntropometricoService
                .existePorId(
                        registroRecomendacionBody
                                .getDatoAntropometricoId())) {
            errores.add(new Error(
                    "datoAntropometricoId",
                    "el id no existe"));
        } else if (recomendacionService.existePorDatoAntropometrico(
                datoAntropometricoService.obtenerPorId(
                        registroRecomendacionBody
                                .getDatoAntropometricoId())
                        .get()
        )) {
            errores.add(new Error(
                    "mensaje",
                    "el mensaje de recomendacion ya existe"));
        }

        return errores;
    }
}
