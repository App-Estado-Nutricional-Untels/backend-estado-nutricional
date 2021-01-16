package com.untels.estadonutricional.service;

import com.untels.estadonutricional.entity.Alumno;
import com.untels.estadonutricional.entity.DatoAntropometrico;
import com.untels.estadonutricional.repository.DatoAntropometricoRepository;
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
    
    public boolean existeRegistrosPorAlumno(Alumno alumno){
        long registros = antropometricoRepository.countByAlumno(alumno);
        return registros>0;
    }
}
