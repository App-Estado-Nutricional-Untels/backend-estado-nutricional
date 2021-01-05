package com.untels.estadonutricional.repository;

import com.untels.estadonutricional.entity.CodigoUniValido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodigoUniValidoRepository extends
        JpaRepository<CodigoUniValido, Integer> {

}
