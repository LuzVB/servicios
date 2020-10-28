package edu.unicundi.entity;

import edu.unicundi.entity.Curso;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-28T02:33:41")
@StaticMetamodel(Estudiante.class)
public class Estudiante_ { 

    public static volatile SingularAttribute<Estudiante, Date> fechaNacimiento;
    public static volatile SingularAttribute<Estudiante, Curso> curso;
    public static volatile SingularAttribute<Estudiante, String> apellido;
    public static volatile SingularAttribute<Estudiante, String> correo;
    public static volatile SingularAttribute<Estudiante, Integer> id;
    public static volatile SingularAttribute<Estudiante, String> nombre;

}