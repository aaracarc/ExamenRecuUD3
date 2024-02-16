package org.iesvdm.pruebarecuud3.service;

import org.iesvdm.pruebarecuud3.dao.ProfesorDAO;
import org.iesvdm.pruebarecuud3.dto.ProfesorDTO;
import org.iesvdm.pruebarecuud3.modelo.Profesor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {

    private ProfesorDAO profesorDAO;

    //Se utiliza inyección automática por constructor del framework Spring.
    //Por tanto, se puede omitir la anotación Autowired
    //@Autowired
    public ProfesorService(ProfesorDAO profesorDAO) {
        this.profesorDAO = profesorDAO;
    }

    public List<Profesor> listAll() {

        return profesorDAO.getAll();

    }

    public Profesor one(Integer id) {
        //Utiliza el método find del ProfesorDAO para obtener un Optional de Profesor basado en el id.
        Optional<Profesor> optPro = profesorDAO.find(id);

        //Verifica si el Optional contiene un profesor.
        if (optPro.isPresent())
            //Si hay un profesor, devuelve el objeto Profesor contenido en el Optional.
            return optPro.get();
        else
            //Si el Optional está vacío, devuelve null.
            return null;
    }

    /**
     * Agrega un nuevo profesor a la base de datos.
     * @param profesor El objeto Profesor que se va a agregar.
     */
    public void newProfesor(Profesor profesor) {
        profesorDAO.create(profesor);
    }

    /**
     * Reemplaza los datos de un profesor existente en la base de datos.
     * @param profesor El objeto Profesor con los nuevos datos.
     */
    public void replaceProfesor(Profesor profesor) {
        profesorDAO.update(profesor);
    }

    /**
     * Elimina un profesor de la base de datos según su identificador único.
     * @param id El identificador único del profesor que se va a eliminar.
     */
    public void deleteProfesor(int id) {
        profesorDAO.delete(id);
    }

    public ProfesorDTO estadisticasProfesor()
    {
        return profesorDAO.estadisticasProfesor();
    }
}