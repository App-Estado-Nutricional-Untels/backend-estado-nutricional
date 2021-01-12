package com.untels.estadonutricional.service;

import com.untels.estadonutricional.entity.DatoAntropometrico;
import com.untels.estadonutricional.entity.Recomendacion;
import com.untels.estadonutricional.repository.RecomendacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecomendacionService {

    @Autowired
    private RecomendacionRepository recomendacionRepository;

    public boolean existePorDatoAntropometrico(
            DatoAntropometrico datoAntropometrico
    ) {
        return recomendacionRepository
                .existsByDatoAntropometrico(datoAntropometrico);
    }

    public void guardar(Recomendacion recomendacion) {
        recomendacionRepository.save(recomendacion);
    }
}
