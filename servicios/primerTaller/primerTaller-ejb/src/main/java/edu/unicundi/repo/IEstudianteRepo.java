/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.repo;

import edu.unicundi.entity.Estudiante;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Luz
 */
@Local 
public interface IEstudianteRepo {

    public List<Estudiante> listar();

    public Estudiante listarPorId(Integer id);

    public void guardar(Estudiante estudiante);

    public void editar(Estudiante estudiante);

    public void eliminar(Estudiante estudiante);
}
