package com.untels.estadonutricional.repository;

import com.untels.estadonutricional.entity.DatoAntropometrico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatoAntropometricoRepository extends
        JpaRepository<DatoAntropometrico, Integer> {

}
