package com.untels.estadonutricional.security.service;

import com.untels.estadonutricional.entity.Alumno;
import com.untels.estadonutricional.entity.Medico;
import com.untels.estadonutricional.entity.Persona;
import com.untels.estadonutricional.enums.SexoNombre;
import com.untels.estadonutricional.repository.AlumnoRepository;
import com.untels.estadonutricional.repository.MedicoRepository;
import com.untels.estadonutricional.repository.PersonaRepository;
import com.untels.estadonutricional.security.entity.Usuario;
import com.untels.estadonutricional.security.enums.RolNombre;
import com.untels.estadonutricional.security.repository.RolRepository;
import com.untels.estadonutricional.security.repository.UsuarioRepository;
import java.util.GregorianCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NuevoUsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    AlumnoRepository alumnoRepository;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Usuario crearUsuarioCompleto(UsuarioCompleto usuariocompleto) {
        Persona persona = new Persona();
        Usuario usuario = new Usuario();

        persona.setNombre(usuariocompleto.getNombre());
        persona.setApepaterno(usuariocompleto.getApePaterno());
        persona.setApematerno(usuariocompleto.getApeMaterno());
        persona.setDni(usuariocompleto.getDni());
        persona.setFechaNacimiento(usuariocompleto.getFechaNacimiento());
        persona.setSexo(usuariocompleto.getSexo());

        personaRepository.save(persona);

        usuario.setCorreoElectronico(usuariocompleto.getCorreoElectronico());
        usuario.setClave(passwordEncoder.encode(usuariocompleto.getClave()));
        usuario.setPersona(persona);

        if (usuariocompleto.getRol().compareTo(RolNombre.ROLE_ALUMNO) == 0) {
            Alumno alumno = new Alumno();
            alumno.setCodigoUniversitario(usuariocompleto.getCodigo());
            alumno.setPersona(persona);

            alumnoRepository.save(alumno);

        } else if (usuariocompleto.getRol().compareTo(RolNombre.ROLE_MEDICO) == 0) {
            Medico medico = new Medico();
            medico.setCodigoMedico(usuariocompleto.getCodigo());
            medico.setPersona(persona);

            medicoRepository.save(medico);
        }

        usuario.setRol(
                rolRepository
                        .findByRolNombre(usuariocompleto.getRol())
                        .get());

        usuarioRepository.save(usuario);

        return usuario;
    }

    public static class UsuarioCompleto {

        private final String nombre;
        private final String apePaterno;
        private final String apeMaterno;
        private final String dni;
        private final String correoElectronico;
        private final String codigo;
        private final GregorianCalendar fechaNacimiento;
        private final SexoNombre sexo;
        private final RolNombre rol;
        private final String clave;

        public UsuarioCompleto(
                String nombre,
                String apePaterno,
                String apeMaterno,
                String dni,
                String correoElectronico,
                String codigo,
                GregorianCalendar fechaNacimiento,
                SexoNombre sexo,
                RolNombre rol,
                String clave
        ) {
            this.nombre = nombre;
            this.apePaterno = apePaterno;
            this.apeMaterno = apeMaterno;
            this.dni = dni;
            this.correoElectronico = correoElectronico;
            this.codigo = codigo;
            this.fechaNacimiento = fechaNacimiento;
            this.sexo = sexo;
            this.rol = rol;
            this.clave = clave;
        }

        public String getNombre() {
            return nombre;
        }

        public String getApePaterno() {
            return apePaterno;
        }

        public String getApeMaterno() {
            return apeMaterno;
        }

        public String getDni() {
            return dni;
        }

        public String getCorreoElectronico() {
            return correoElectronico;
        }

        public String getCodigo() {
            return codigo;
        }

        public GregorianCalendar getFechaNacimiento() {
            return fechaNacimiento;
        }

        public SexoNombre getSexo() {
            return sexo;
        }

        public RolNombre getRol() {
            return rol;
        }

        public String getClave() {
            return clave;
        }

    }

}
