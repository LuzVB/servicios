/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.repo;

import edu.unicundi.entity.Libro;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Luz
 */
@Local 
public interface ILibroRepo {
    
    public List<Libro> listar();
        
    public Libro listarPorId(Integer id);
    
    public void guardar(Libro libro);
    
    public void editar(Libro libro);
   
    public void eliminar(Libro libro);
        
}
