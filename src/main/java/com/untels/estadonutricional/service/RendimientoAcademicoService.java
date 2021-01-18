package com.untels.estadonutricional.service;

import com.untels.estadonutricional.enums.RendimientoAcademico;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RendimientoAcademicoService {

    public List<RendimientoAcademico> listarRendimientosAcademicos() {
        return Arrays.asList(RendimientoAcademico.values());
    }
}
