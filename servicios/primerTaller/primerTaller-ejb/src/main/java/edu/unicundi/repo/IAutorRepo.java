/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.repo;

import edu.unicundi.entity.Autor;
import java.util.List;

/**
 *
 * @author Valentina
 */
public interface IAutorRepo {
    
    public List<Autor> listar();
    
    public Autor listarPorId(Integer id);
    
    public Autor listarPorIdA(Integer id); 
             
    public void guardar(Autor autor);
    
    public void editar(Autor autor);
   
    public void eliminar(Autor autor);  
}
