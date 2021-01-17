package com.untels.estadonutricional.repository;

import com.untels.estadonutricional.entity.Alumno;
import com.untels.estadonutricional.entity.DatoAntropometrico;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DatoAntropometricoRepository extends
        JpaRepository<DatoAntropometrico, Integer> {

    public Optional<DatoAntropometrico> findByAlumno(Alumno alumno);

    public long countByAlumno(Alumno alumno);

    public boolean existsByAlumno(Alumno alumno);

    public boolean existsById(int id);

    public Optional<DatoAntropometrico> findById(int id);

    @Query(
            value = "select * from dato_antropometrico d inner join alumno a on d.alumno_id=a.id where a.id = ?1 order by d.fecha_registro desc limit 1",
            nativeQuery = true
    )
    public Optional<DatoAntropometrico> findLastByAlumno(int id);
    
    @Query(
            value = "select * from dato_antropometrico d where d.alumno_id=?1",
            nativeQuery = true
    )
    public List<DatoAntropometrico> findAllByAlumnoId(int id);

    @Query(
            value = "SELECT AVG(valor_imc) as promedioImc, "
            + "MONTH(fecha_registro) as mes, "
            + "YEAR(fecha_registro) as anio "
            + "FROM dato_antropometrico "
            + "GROUP BY mes, anio;",
            nativeQuery = true
    )
    public List<PromedioIMCGrupal> findAllByPromedioIMCGrupal();

    @Query(
            value = "SELECT AVG(valor_icc) as promedioIcc, "
            + "MONTH(fecha_registro) as mes, "
            + "YEAR(fecha_registro) as anio "
            + "FROM dato_antropometrico "
            + "GROUP BY mes, anio;",
            nativeQuery = true
    )
    public List<PromedioICCGrupal> findAllByPromedioICCGrupal();

    public static interface PromedioIMCGrupal {

        Float getPromedioIMC();

        Integer getMes();

        Integer getAnio();
    }

    public static interface PromedioICCGrupal {

        Float getPromedioICC();

        Integer getMes();

        Integer getAnio();
    }
}
