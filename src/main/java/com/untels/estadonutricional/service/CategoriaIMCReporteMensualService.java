package com.untels.estadonutricional.service;

import com.untels.estadonutricional.entity.Alumno;
import com.untels.estadonutricional.entity.DatoAntropometrico;
import com.untels.estadonutricional.enums.CategoriaIMC;
import com.untels.estadonutricional.enums.NivelEstres;
import com.untels.estadonutricional.enums.RendimientoAcademico;
import com.untels.estadonutricional.enums.SexoNombre;
import com.untels.estadonutricional.repository.AlumnoRepository;
import com.untels.estadonutricional.repository.DatoAntropometricoRepository;
import com.untels.estadonutricional.utils.StringUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoriaIMCReporteMensualService {

    @Autowired
    DatoAntropometricoRepository datoAntropometricoRepository;

    @Autowired
    AlumnoRepository alumnoRepository;

    public boolean existeReporte(CategoriaIMC categoriaIMC, int mes, int anio) {
        return datoAntropometricoRepository
                .findAllByMesAnioCategoriaIMC(mes, anio, categoriaIMC.name())
                .size() > 0;
    }

    public CategoriaIMCReporteMensual generarReporte(
            CategoriaIMC categoriaIMC,
            int mes,
            int anio
    ) {
        List<DatoAntropometrico> datosAntropometricos
                = datoAntropometricoRepository
                        .findAllByMesAnioCategoriaIMC(
                                mes, anio, categoriaIMC.name());

        List<Alumno> alumnos = datosAntropometricos
                .stream()
                .map(dato -> dato.getAlumno())
                .collect(Collectors.toList());

        int totalAlumnosFiltrados = alumnos.size();
        float porcAlumnos = (float) datosAntropometricos.size()
                / (float) alumnoRepository.countAll();
        int edadPromedio = (int) alumnos.stream()
                .mapToInt(alumno -> alumno.getPersona().getEdad())
                .average()
                .getAsDouble();
        float actividadFisicaPromedio = (float) datosAntropometricos.stream()
                .mapToDouble(dato -> dato.getActividadFisica())
                .average()
                .getAsDouble();

        CategoriaIMCReporteMensual reporte = new CategoriaIMCReporteMensual(
                categoriaIMC,
                mes,
                anio,
                totalAlumnosFiltrados,
                porcAlumnos,
                edadPromedio,
                actividadFisicaPromedio,
                buildSexo(alumnos),
                buildEstres(datosAntropometricos),
                buildRendimientoAcademico(datosAntropometricos)
        );

        return reporte;
    }

    private Map<String, CategoriaIMCReporteMensual.AgrupadorAlumnos> buildSexo(
            List<Alumno> alumnos
    ) {
        Map<String, CategoriaIMCReporteMensual.AgrupadorAlumnos> sexo
                = new HashMap<>();

        long total = alumnos.size();
        long alumnosFemeninos = alumnos.stream()
                .filter(alumno -> alumno.getPersona().getSexo() == SexoNombre.F)
                .collect(Collectors.counting());

        long alumnosMasculinos = alumnos.stream()
                .filter(alumno -> alumno.getPersona().getSexo() == SexoNombre.M)
                .collect(Collectors.counting());

        float porcFemeninos = (total != 0)
                ? (float) alumnosFemeninos / (float) total : 0;

        float porcMasculinos = (total != 0)
                ? (float) alumnosMasculinos / (float) total : 0;

        sexo.put("femenino", new CategoriaIMCReporteMensual.AgrupadorAlumnos(
                porcFemeninos, (int) alumnosFemeninos));

        sexo.put("masculino", new CategoriaIMCReporteMensual.AgrupadorAlumnos(
                porcMasculinos, (int) alumnosMasculinos));

        return sexo;
    }

    private Map<String, CategoriaIMCReporteMensual.AgrupadorAlumnos> buildEstres(
            List<DatoAntropometrico> datos
    ) {
        Map<String, CategoriaIMCReporteMensual.AgrupadorAlumnos> estres
                = new HashMap<>();

        long total = datos.size();

        for (NivelEstres nivelEstres : NivelEstres.values()) {
            long totalNivelEstres = datos.stream()
                    .filter(dato -> dato.getNivelEstres() == nivelEstres)
                    .collect(Collectors.counting());

            float porcentaje = (total != 0)
                    ? (float) totalNivelEstres / (float) total : 0;

            estres.put(
                    StringUtils.snakeUpperToCamelCase(nivelEstres.name()),
                    new CategoriaIMCReporteMensual.AgrupadorAlumnos(
                            porcentaje,
                            (int) totalNivelEstres));

        }

        return estres;
    }

    private Map<String, CategoriaIMCReporteMensual.AgrupadorAlumnos>
            buildRendimientoAcademico(
                    List<DatoAntropometrico> datos
            ) {
        Map<String, CategoriaIMCReporteMensual.AgrupadorAlumnos> rendAcademico
                = new HashMap<>();

        long total = datos.size();

        for (RendimientoAcademico rendimiento : RendimientoAcademico.values()) {
            long totalRendimiento = datos.stream()
                    .filter(dato -> dato.getRendimientoAcademico() == rendimiento)
                    .collect(Collectors.counting());

            float porcentaje = (total != 0)
                    ? (float) totalRendimiento / (float) total : 0;

            rendAcademico.put(
                    StringUtils.snakeUpperToCamelCase(rendimiento.name()),
                    new CategoriaIMCReporteMensual.AgrupadorAlumnos(
                            porcentaje,
                            (int) totalRendimiento));

        }

        return rendAcademico;
    }

    public static class CategoriaIMCReporteMensual {

        private CategoriaIMC categoriaIMC;
        private int mes;
        private int anio;
        private int totalAlumnos;
        private float porcentajeAlumnos;
        private int edadPromedio;
        private float actividadFisicaPromedio;
        private Map<String, AgrupadorAlumnos> sexo;
        private Map<String, AgrupadorAlumnos> estres;
        private Map<String, AgrupadorAlumnos> rendimientoAcademico;

        public CategoriaIMCReporteMensual(
                CategoriaIMC categoriaIMC,
                int mes,
                int anio,
                int totalAlumnos,
                float porcentajeAlumnos,
                int edadPromedio,
                float actividadFisicaPromedio,
                Map<String, AgrupadorAlumnos> sexo,
                Map<String, AgrupadorAlumnos> estres,
                Map<String, AgrupadorAlumnos> rendimientoAcademico
        ) {
            this.categoriaIMC = categoriaIMC;
            this.mes = mes;
            this.anio = anio;
            this.totalAlumnos = totalAlumnos;
            this.porcentajeAlumnos = porcentajeAlumnos;
            this.edadPromedio = edadPromedio;
            this.actividadFisicaPromedio = actividadFisicaPromedio;
            this.sexo = sexo;
            this.estres = estres;
            this.rendimientoAcademico = rendimientoAcademico;
        }

        public CategoriaIMC getCategoriaIMC() {
            return categoriaIMC;
        }

        public void setCategoriaIMC(CategoriaIMC categoriaIMC) {
            this.categoriaIMC = categoriaIMC;
        }

        public int getMes() {
            return mes;
        }

        public void setMes(int mes) {
            this.mes = mes;
        }

        public int getAnio() {
            return anio;
        }

        public void setAnio(int anio) {
            this.anio = anio;
        }

        public int getTotalAlumnos() {
            return totalAlumnos;
        }

        public void setTotalAlumnos(int totalAlumnos) {
            this.totalAlumnos = totalAlumnos;
        }

        public float getPorcentajeAlumnos() {
            return porcentajeAlumnos;
        }

        public void setPorcentajeAlumnos(float porcentajeAlumnos) {
            this.porcentajeAlumnos = porcentajeAlumnos;
        }

        public int getEdadPromedio() {
            return edadPromedio;
        }

        public void setEdadPromedio(int edadPromedio) {
            this.edadPromedio = edadPromedio;
        }

        public float getActividadFisicaPromedio() {
            return actividadFisicaPromedio;
        }

        public void setActividadFisicaPromedio(float actividadFisicaPromedio) {
            this.actividadFisicaPromedio = actividadFisicaPromedio;
        }

        public String getMensajeClasificacion() {
            String msg = "El " + this.porcentajeAlumnos + "% de los estudiantes se "
                    + "encuentra clasificado en el grupo con " + this.categoriaIMC + " "
                    + "y se caracteriza por ser " + this.sexo.get("masculino").porcentaje + "% "
                    + "Masculino y " + this.sexo.get("femenino").porcentaje + "% Femenino, "
                    + "edad promedio " + this.edadPromedio + ", horas de actividad "
                    + "física promedio " + this.actividadFisicaPromedio + "; ";

            Iterator it = this.estres.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                msg += ((AgrupadorAlumnos) pair.getValue()).porcentaje
                        + "% con estrés "
                        + pair.getKey()
                        + ", ";
                it.remove();
            }

            it = this.rendimientoAcademico.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                msg += ((AgrupadorAlumnos) pair.getValue()).porcentaje
                        + "% con rendimiento académico "
                        + pair.getKey()
                        + ", ";
                it.remove();
            }

            msg += "siendo un total de " + this.totalAlumnos + " que conforman este grupo";

            return msg;

        }

        public Map<String, AgrupadorAlumnos> getSexo() {
            return sexo;
        }

        public void setSexo(Map<String, AgrupadorAlumnos> sexo) {
            this.sexo = sexo;
        }

        public Map<String, AgrupadorAlumnos> getEstres() {
            return estres;
        }

        public void setEstres(Map<String, AgrupadorAlumnos> estres) {
            this.estres = estres;
        }

        public Map<String, AgrupadorAlumnos> getRendimientoAcademico() {
            return rendimientoAcademico;
        }

        public void setRendimientoAcademico(Map<String, AgrupadorAlumnos> rendimientoAcademico) {
            this.rendimientoAcademico = rendimientoAcademico;
        }

        public static class AgrupadorAlumnos {

            private float porcentaje;
            private int totalAlumnos;

            public AgrupadorAlumnos(
                    float porcentaje,
                    int totalAlumnos
            ) {
                this.porcentaje = porcentaje;
                this.totalAlumnos = totalAlumnos;
            }

            public float getPorcentaje() {
                return porcentaje;
            }

            public void setPorcentaje(float porcentaje) {
                this.porcentaje = porcentaje;
            }

            public int getTotalAlumnos() {
                return totalAlumnos;
            }

            public void setTotalAlumnos(int totalAlumnos) {
                this.totalAlumnos = totalAlumnos;
            }
        }
    }
}
