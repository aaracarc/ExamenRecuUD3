package org.iesvdm.pruebarecuud3.dao;

import org.iesvdm.pruebarecuud3.dto.ProfesorDTO;
import org.iesvdm.pruebarecuud3.modelo.Profesor;

import java.util.List;
import java.util.Optional;


/**
 * Interfaz que define las operaciones de acceso a datos para la entidad org.iesvdm.pruebarecuud3.modelo.Profesor.
 * Proporciona métodos para la creación, recuperación, actualización y eliminación de profesors en la base de datos.
 * Las implementaciones concretas de esta interfaz deben manejar las interacciones con el almacenamiento de datos,
 * como una base de datos relacional o cualquier otro mecanismo de persistencia.
 */
public interface ProfesorDAO {

    /**
     * Crea un nuevo profesor en la base de datos.
     * @param profesor El objeto org.iesvdm.pruebarecuud3.modelo.Profesor que se va a crear.
     */
    void create(Profesor profesor);

    /**
     * Recupera todos los profesors almacenados en la base de datos.
     * @return Una lista de objetos Profesor que representan a todos los profesors en la base de datos.
     */
    List<Profesor> getAll();

    /**
     * Recupera un profesor específico basado en su identificador único.
     * @param id El identificador único del profesor a recuperar.
     * @return Un objeto Optional<Profesor> que contiene el profesor recuperado, o está vacío si no se encuentra.
     */
    Optional<Profesor> find(int id);

    /**
     * Actualiza la información de un profesor existente en la base de datos.
     * @param profesor El objeto Profesor con la información actualizada.
     */
    void update(Profesor profesor);

    /**
     * Elimina un profesor de la base de datos según su identificador único.
     * @param id El identificador único del profesor a eliminar.
     */
    void delete(long id);

    public ProfesorDTO estadisticasProfesor();
}