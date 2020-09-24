/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service.impl;

import edu.unicundi.dto.Materia;
import edu.unicundi.dto.Profesor;
import edu.unicundi.exception.IdVacioException;
import edu.unicundi.exception.ListaVaciaException;
import edu.unicundi.exception.NoValidoException;
import edu.unicundi.service.IProfesorService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Valentina
 */
@Stateless
//@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
public class ProfesorServiceImpl extends DatosImpl implements IProfesorService {
    /**
     * Lista de profesores
     */
    private List<Profesor> listaProfesores;
    /**
     * Lista de materias
     */
    private List<Materia> listaMaterias;
    /**
     * Id del profesor
     */
    private int idProfesor;
    /**
     * boolean estado del retorno de la consulta sql
     */
    private boolean estado = true;

    /**
     * Metodo que retorna la lista de todos los profesores registrados
     */
    @Override
    public void listarProfesor() {
        try {
            listaProfesores = new ArrayList<>();
            java.sql.Statement st = conexion.createStatement();
            System.out.println("AQUIII " + listaMaterias);
            String sql = "SELECT cedula_profesor, nombre_profesor, apellido_profesor, correo_profesor, edad_correo, id_profesor FROM public.profesor;";
            ResultSet result = st.executeQuery(sql);
            if (result.next() == false) {
                throw new ListaVaciaException("No hay datos registrados");
            } else {
                do {
                    this.idProfesor = Integer.parseInt(result.getString("id_profesor"));
                    int edad = Integer.parseInt(result.getString("edad_correo"));
                    int cedula = Integer.parseInt(result.getString("cedula_profesor"));
                    listaMateriasProfesor();
                    listaProfesores.add(new Profesor(this.idProfesor, edad, cedula, result.getString("nombre_profesor"), result.getString("apellido_profesor"), result.getString("correo_profesor"), this.getListaMaterias()));
                } while (result.next());
            }
        } catch (SQLException ex) {

        }
    }

    /**
     * metodo que lista al profesor por su cedula
     *
     * @param cedula cedula del profesor
     * @throws Exception
     */
    @Override
    public void listarProfesorCedula(int cedula) {
        traerCedula(cedula);
        if (this.estado == false) {
            throw new NoValidoException("Cédula no valida");
        }
    }

    /**
     * metodo que lista al profesor por la materia
     *
     * @param materia material del profesor
     * @throws Exception
     */
    @Override
    public void listarProfesorMateria(String materia) {
        traerMateria(materia);
        if (this.estado == false) {
            throw new NoValidoException("Materia no valida");
        }
    }

    /**
     * metodo para arma la lista del profesor, por la materia
     *
     * @param materia material del profesor
     * @throws SQLException
     */
    @Override
    public void traerMateria(String materia) {
        try {
            listaProfesores = new ArrayList<>();
            java.sql.Statement st = conexion.createStatement();
            String sql = "SELECT profesor.id_profesor, profesor.cedula_profesor, profesor.nombre_profesor, profesor.apellido_profesor, profesor.correo_profesor, profesor.edad_correo FROM materia,profesor,profesor_materia where materia.id_materia=profesor_materia.id_materia and profesor_materia.id_profesor=profesor.id_profesor and materia.nombre_materia='" + materia + "';";
            ResultSet result = st.executeQuery(sql);
            if (result.next() == false) {
                this.estado = false;
            } else {
                do {
                    this.idProfesor = Integer.parseInt(result.getString("id_profesor"));
                    int edad = Integer.parseInt(result.getString("edad_correo"));
                    int numeroCedula = Integer.parseInt(result.getString("cedula_profesor"));
                    listaMateriasProfesor();
                    listaProfesores.add(new Profesor(this.idProfesor, edad, numeroCedula, result.getString("nombre_profesor"), result.getString("apellido_profesor"), result.getString("correo_profesor"), this.getListaMaterias()));
                } while (result.next());
            }
        } catch (SQLException ex) {

        }
    }

    /**
     * metodo para armar la lista del profesor, por la cedula
     *
     * @param cedula cedula del profesor
     */
    @Override
    public void traerCedula(int cedula) {
        try {
            listaProfesores = new ArrayList<>();
            java.sql.Statement st = conexion.createStatement();
            String sql = "SELECT id_profesor, cedula_profesor, nombre_profesor, apellido_profesor, correo_profesor, edad_correo FROM public.profesor where cedula_profesor=" + cedula + ";";
            ResultSet result = st.executeQuery(sql);
            if (result.next() == false) {
                this.estado = false;
            } else {
                do {
                    this.idProfesor = Integer.parseInt(result.getString("id_profesor"));
                    int edad = Integer.parseInt(result.getString("edad_correo"));
                    int numeroCedula = Integer.parseInt(result.getString("cedula_profesor"));
                    listaMateriasProfesor();
                    listaProfesores.add(new Profesor(this.idProfesor, edad, numeroCedula, result.getString("nombre_profesor"), result.getString("apellido_profesor"), result.getString("correo_profesor"), this.getListaMaterias()));
                } while (result.next());
            }
        } catch (SQLException ex) {

        }
    }

    /**
     * Metodo que retorna la lista de las materias de acuerdo al profesor
     *
     * @throws SQLException
     */
    @Override
    public void listaMateriasProfesor() throws SQLException {

        listaMaterias = new ArrayList<>();
        java.sql.Statement st = conexion.createStatement();
        try {
            String sql2 = "SELECT materia.id_materia,materia.nombre_materia,materia.cupos_materia,materia.creditos_materia FROM materia,profesor_materia where materia.id_materia=profesor_materia.id_materia and profesor_materia.id_profesor=" + this.idProfesor + ";";
            ResultSet result2 = st.executeQuery(sql2);
            while (result2.next()) {
                int idMateria = Integer.parseInt(result2.getString("id_materia"));
                int cuposMateria = Integer.parseInt(result2.getString("cupos_materia"));
                int creditosMateria = Integer.parseInt(result2.getString("creditos_materia"));
                listaMaterias.add(new Materia(idMateria, result2.getString("nombre_materia"), cuposMateria, creditosMateria));
            }
        } catch (SQLException | NumberFormatException e) {

        }
    }

    /**
     * metodo para modificar datos del profesor
     *
     * @param profesor
     */
    @Override
    public void editarProfesor(Profesor profesor) {

        if (profesor.getNombre() == null || profesor.getId() == null
                || profesor.getEdad() == null || profesor.getCorreo() == null
                || profesor.getApellido() == null || profesor.getCedula() == null) {
            throw new IdVacioException("Uno de los atributos del JSON esta vacio");
        } else {
            this.idProfesor = Integer.parseInt(profesor.getId().toString());
            int edad = Integer.parseInt(profesor.getEdad().toString());
            int cedula = Integer.parseInt(profesor.getCedula().toString());
            System.out.println(profesor.getNombre() + " " + profesor.getApellido() + " " + profesor.getEdad());
            String cadenaSql = "UPDATE public.profesor SET  id_profesor='" + this.idProfesor + "',cedula_profesor='" + cedula + "',nombre_profesor='" + profesor.getNombre() + "',apellido_profesor='" + profesor.getApellido() + "',correo_profesor='" + profesor.getCorreo() + "',edad_correo='" + edad + "' WHERE id_profesor=" + this.idProfesor + ";";
            modifacionBaseDatos(cadenaSql);
        }
    }

    /**
     * metodo para registar al profesor
     *
     * @param profesorInsertar
     * @throws Exception
     */
    @Override
    public void insertarProfesor(Profesor profesorInsertar) throws Exception {

        if (profesorInsertar.getNombre() == null || profesorInsertar.getEdad() == null || profesorInsertar.getCorreo() == null
                || profesorInsertar.getApellido() == null || profesorInsertar.getCedula() == null) {
            throw new IdVacioException("Uno de los atributos del JSON esta vacio");
        } else {
            traerCedula(Integer.parseInt(profesorInsertar.getCedula().toString()));
            if (estado == false) {
                String cadenaSql = " INSERT INTO public.profesor(cedula_profesor, nombre_profesor, apellido_profesor, correo_profesor, edad_correo)"
                        + "VALUES (" + Integer.parseInt(profesorInsertar.getCedula().toString()) + ",'"
                        + profesorInsertar.getNombre() + "','" + profesorInsertar.getApellido() + "','" + profesorInsertar.getCorreo() + "'," + Integer.parseInt(profesorInsertar.getEdad().toString()) + ");";
                modifacionBaseDatos(cadenaSql);
                traerUltimoID();
                insertarTablaAsociativa(profesorInsertar.getListaMateria(), this.idProfesor);
            } else {
                throw new NoValidoException("La cedula ya se encuentra registrada");
            }
        }

    }

    /**
     * metodo para traer el ultimo id del profesor registrado
     *
     * @throws SQLException
     */
    @Override
    public void traerUltimoID() throws SQLException {
        java.sql.Statement st = conexion.createStatement();
        String sql = "SELECT MAX(id_profesor) as id_profesor FROM public.profesor;";
        ResultSet result = st.executeQuery(sql);
        while (result.next()) {
            this.idProfesor = Integer.parseInt(result.getString("id_profesor"));
        }
        System.out.println(this.idProfesor);
    }

    public void insertarTablaAsociativa(List<Materia> listaMateria, int idProfesor) {
        for (Materia materia : listaMateria) {
            String cadenaSql = " INSERT INTO public.profesor_materia(id_profesor, id_materia)"
                    + "VALUES (" + idProfesor + "," + Integer.parseInt(materia.getId().toString()) + ");";
            modifacionBaseDatos(cadenaSql);
        }
    }

    /**
     * metodo para eliminar al profesor
     *
     * @param idProfesor
     */
    @Override
    public void eliminarProfesor(int idProfesor) {
        try {
            listaProfesores = new ArrayList<>();
            java.sql.Statement st = conexion.createStatement();
            String sql = "SELECT id_profesor, cedula_profesor, nombre_profesor, apellido_profesor, correo_profesor, edad_correo FROM public.profesor where id_profesor=" + idProfesor + ";";
            ResultSet result = st.executeQuery(sql);
            if (result.next() == false) {
                throw new NoValidoException("Id no encontrado");
            } else {
                String cadenaSql = "DELETE FROM public.profesor WHERE id_profesor" + "=" + idProfesor + ";";
                modifacionBaseDatos(cadenaSql);
            }
        } catch (SQLException ex) {

        }
    }

    @Override
    public List<Profesor> getListaProfesores() {
        return listaProfesores;
    }

    public void setListaProfesores(List<Profesor> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

    public List<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(List<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }
}
