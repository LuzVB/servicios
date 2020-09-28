/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service;

import edu.unicundi.dto.Profesor;
import edu.unicundi.dto.ProfesorA;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.ObjectNotFoundException;

/**
 *
 * @author Valentina
 */
@Local
public interface IProfesorService {
    public void listarProfesor();

    public void listarProfesorCedula(int cedula);

    public void listarProfesorMateria(String materia);

    public void traerMateria(String materia);

    public void traerCedula(int cedula);

    public void listaMateriasProfesor() throws SQLException;

    public void editarProfesor(Profesor profesor);

    public void insertarProfesor(Profesor profesorInsertar) throws Exception;

    public void traerUltimoID() throws SQLException;

    public void eliminarProfesor(int idProfesor);

    public List<Profesor> getListaProfesores();
    
    public void registroProfesor(ProfesorA profesor);
    
    public List<ProfesorA> retornarProfesores() throws ObjectNotFoundException;
}
