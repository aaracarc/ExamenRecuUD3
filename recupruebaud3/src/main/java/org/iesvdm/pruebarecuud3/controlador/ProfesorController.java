package org.iesvdm.pruebarecuud3.controlador;


import jakarta.validation.Valid;
import org.iesvdm.pruebarecuud3.dto.ProfesorDTO;
import org.iesvdm.pruebarecuud3.modelo.Profesor;
import org.iesvdm.pruebarecuud3.service.ProfesorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
//Se puede fijar ruta base de las peticiones de este controlador.
//Los mappings de los métodos tendrían este valor /profesores como
//prefijo.
//@RequestMapping("/profesores")
public class ProfesorController {

    private ProfesorService profesorService;

    //Se utiliza inyección automática por constructor del framework Spring.
    //Por tanto, se puede omitir la anotación Autowired
    //@Autowired
    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    /**
     * @param model se utiliza para agregar atributos que se pasarán a la vista.
     * @return Una cadena, el nombre de la vista que se debe renderizar.
     */
    //@RequestMapping(value = "/profesores", method = RequestMethod.GET)
    //equivalente a la siguiente anotación
    @GetMapping("/profesores") //Al no tener ruta base para el controlador, cada método tiene que tener la ruta completa

    public String listar(Model model) {

        List<Profesor> listaProfesores =  profesorService.listAll();
        ProfesorDTO profesorDTO = profesorService.estadisticasProfesor();
        //Se agrega la lista de profesores al modelo con el nombre "listaProfesores". Ya está disponible para ser utilizada en la vista.
        model.addAttribute("listaProfesores", listaProfesores);
        model.addAttribute("ProfesorDTO", profesorDTO);

        return "profesores";

    }
    @GetMapping("/profesores/crear")
    public String crear(Model model) {

        Profesor profesor = new Profesor();
        model.addAttribute("profesor", profesor);

        return "crear-profesor";

    }
    @PostMapping("/profesores/crear")
    public String submitCrear(@Valid @ModelAttribute("profesor") Profesor profesor, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "crear-profesor";
        }

        profesorService.newProfesor(profesor);

        return "redirect:/profesores" ;

    }
//    //El parámetro @PathVariable Integer id indica que el valor de la variable de ruta {id} en la URL se asignará a la variable id en el método.
//    @GetMapping("/profesores/{id}")
//    public String detalle(Model model, @PathVariable Integer id ) {
//
//        Profesor profesor = profesorService.one(id);
//        model.addAttribute("profesor", profesor);
//
//        return "detalle-profesor";
//
//    }
    //    //El id se pasa como parámetro para realizar la consulta sobre él
//Pueden tener la misma ruta porque realizan diferentes acciones: la visualización del formulario de creación y el procesamiento del formulario enviado

//    @GetMapping("/profesores/editar/{id}")
//    public String editar(Model model, @PathVariable Integer id) {
//
//        Profesor profesor = profesorService.one(id);
//        model.addAttribute("profesor", profesor);
//
//        return "editar-profesor";
//
//    }
//
//
//    @PostMapping("/profesores/editar/{id}")
//    public String submitEditar(@Valid @ModelAttribute("profesor") Profesor profesor, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return "editar-profesor";
//        }
//        profesorService.replaceProfesor(profesor);
//
//        return "redirect:/profesores";
//    }
//
//    @PostMapping("/profesores/borrar/{id}")
//    public RedirectView submitBorrar(@PathVariable Integer id) {
//
//        profesorService.deleteProfesor(id);
//
//        return new RedirectView("/profesores");
//    }
}