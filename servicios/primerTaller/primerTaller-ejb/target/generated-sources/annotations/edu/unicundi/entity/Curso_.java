package edu.unicundi.entity;

import edu.unicundi.entity.Estudiante;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-28T02:33:41")
@StaticMetamodel(Curso.class)
public class Curso_ { 

    public static volatile SingularAttribute<Curso, String> descripcion;
    public static volatile ListAttribute<Curso, Estudiante> estudiante;
    public static volatile SingularAttribute<Curso, String> aula;
    public static volatile SingularAttribute<Curso, Integer> id;

}