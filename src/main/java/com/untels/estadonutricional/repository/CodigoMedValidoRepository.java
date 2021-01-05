package com.untels.estadonutricional.repository;

import com.untels.estadonutricional.entity.CodigoMedValido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodigoMedValidoRepository extends
        JpaRepository<CodigoMedValido, Integer> {

}
