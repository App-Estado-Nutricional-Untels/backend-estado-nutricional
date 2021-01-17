package com.untels.estadonutricional.service;

import com.untels.estadonutricional.dto.response.EvolucionIMC;
import com.untels.estadonutricional.entity.Alumno;
import com.untels.estadonutricional.entity.DatoAntropometrico;
import com.untels.estadonutricional.repository.DatoAntropometricoRepository;
import com.untels.estadonutricional.repository.DatoAntropometricoRepository.PromedioICCGrupal;
import com.untels.estadonutricional.repository.DatoAntropometricoRepository.PromedioIMCGrupal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DatoAntropometricoService {

    @Autowired
    private DatoAntropometricoRepository antropometricoRepository;

    public boolean existePorAlumno(Alumno alumno) {
        return antropometricoRepository.existsByAlumno(alumno);
    }

    public boolean existePorId(int id) {
        return antropometricoRepository.existsById(id);
    }

    public Optional<DatoAntropometrico> obtenerPorId(int id) {
        return antropometricoRepository.findById(id);
    }

    public void guardar(DatoAntropometrico datoAntropometrico) {
        antropometricoRepository.save(datoAntropometrico);
    }

    public List<DatoAntropometrico> listarDatosAntropometricos(int id) {
        return antropometricoRepository.findAllByAlumnoId(id);
    }

    public List<EvolucionIMC> listarEvolucionIMCPorAlumnoId(int id){
        List<EvolucionIMC> registrosimc = evolucionIMC(antropometricoRepository.findAllByAlumnoId(id));
        return registrosimc;
    }
    
    public boolean existeRegistrosPorAlumno(Alumno alumno) {
        long registros = antropometricoRepository.countByAlumno(alumno);
        return registros > 0;
    }

    public List<PromedioIMCGrupal> listarPromedioIMCGrupal() {
        return antropometricoRepository.findAllByPromedioIMCGrupal();
    }

    public List<PromedioICCGrupal> listarPromedioICCGrupal() {
        return antropometricoRepository.findAllByPromedioICCGrupal();
    }
    
    private List<EvolucionIMC> evolucionIMC(List<DatoAntropometrico> list){
        List<EvolucionIMC> registrosimc = new ArrayList<EvolucionIMC>();
        
        for(DatoAntropometrico datoantro: list){
            String fechaRegistro = datoantro.getFechaRegistro().get(Calendar.DATE)+"/"+(datoantro.getFechaRegistro().get(Calendar.MONTH)+1)+"/"+datoantro.getFechaRegistro().get(Calendar.YEAR);
            registrosimc.add(new EvolucionIMC(datoantro.getValorIMC(), fechaRegistro));
        }
        
        return registrosimc;
    }
}
