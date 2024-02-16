package org.iesvdm.pruebarecuud3.modelo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

//La anotación @Data de lombok proporcionará el código de:
//getters/setters, toString, equals y hashCode
//propio de los objetos POJOS o tipo Beans
@Data
//Para generar un constructor con lombok con todos los args
@AllArgsConstructor
public class Profesor {

    //Atributos

    private int id;
    @Pattern(regexp = "((([X-Z])|([LM])){1}([-]?)((\\d){7})([-]?)([A-Z]{1}))|((\\d{8})([-]?)([A-Z]))")
    private String nif;

    @NotBlank(message = "{msg.valid.not.blank}")
    @Size(min = 3, message = "{msg.valid.size.min}")
    private String nombre;

    private String apellido1;
    private String apellido2;

    @Size(max=25, message = "{msg.valid.size.max}")
    private String ciudad;

    private String direccion;
    private String telefono;
    private Date fecha_nacimiento;
    private String tipo;

    private Departamento departamento;

    public Profesor() {}
}