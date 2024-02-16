package org.iesvdm.pruebarecuud3.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.pruebarecuud3.dao.ProfesorDAO;
import org.iesvdm.pruebarecuud3.dto.ProfesorDTO;
import org.iesvdm.pruebarecuud3.modelo.Departamento;
import org.iesvdm.pruebarecuud3.modelo.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class ProfesorDAOImpl implements ProfesorDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void create(Profesor profesor) {
        String sqlInsert = """
							INSERT INTO persona (nif, nombre, apellido1, apellido2, ciudad, direccion, telefono, fecha_nacimiento, tipo)
							VALUES  (     ?,         ?,         ?,       ?,         ?, ?, ?, ?, ?)
						   """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        //Con recuperación de id generado
        int rows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[] { "id" });
            int idx = 1;
            ps.setString(idx++, profesor.getNif());
            ps.setString(idx++, profesor.getNombre());
            ps.setString(idx++, profesor.getApellido1());
            ps.setString(idx++, profesor.getApellido2());
            ps.setString(idx++, profesor.getCiudad());
            ps.setString(idx++, profesor.getDireccion());
            ps.setString(idx++, profesor.getTelefono());
            java.sql.Date fechaSql = new java.sql.Date(profesor.getFecha_nacimiento().getTime());
            ps.setDate(idx++, fechaSql);
            ps.setString(idx, profesor.getTipo());
            return ps;
        },keyHolder);

        profesor.setId(keyHolder.getKey().intValue());

        log.info("Insertados {} registros.", rows);
    }

    @Override
    public List<Profesor> getAll() {
        List<Profesor> listaProfesores = jdbcTemplate.query(
                "SELECT persona.*, departamento.nombre DN FROM persona INNER JOIN profesor ON persona.id = profesor.id_profesor INNER JOIN departamento ON profesor.id_departamento = departamento.id",
                (rs, rowNum) -> new Profesor(
                        rs.getInt("id"),
                        rs.getString("nif"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("ciudad"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("tipo"),
                        new Departamento(
                                rs.getString("DN")
                        ))
        );

        log.info("Devueltos {} registros.", listaProfesores.size());

        return listaProfesores;
    }

    @Override
    public Optional<Profesor> find(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Profesor profesor) {

    }

    @Override
    public void delete(long id) {

    }
    @Override
    public ProfesorDTO estadisticasProfesor() {
        ProfesorDTO profesorDTO;

        int profesoresTotales = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) total_profesores FROM profesor",
                Integer.class
        );

        int asignaturasTotales = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM asignatura WHERE id_profesor IN ( SELECT id_profesor FROM persona WHERE tipo = 'catedratico')",
                Integer.class
        );

        profesorDTO = new ProfesorDTO(profesoresTotales, asignaturasTotales);

        return profesorDTO;
    }


}