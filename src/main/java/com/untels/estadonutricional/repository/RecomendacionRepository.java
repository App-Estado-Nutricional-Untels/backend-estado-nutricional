package com.untels.estadonutricional.repository;

import com.untels.estadonutricional.entity.Recomendacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecomendacionRepository extends
        JpaRepository<Recomendacion, Integer> {

}
