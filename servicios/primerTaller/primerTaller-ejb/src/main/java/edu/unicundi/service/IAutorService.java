/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service;

import edu.unicundi.dto.AutorP;
import edu.unicundi.entity.Autor;
import edu.unicundi.exception.ObjectNotFoundException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Valentina
 */
@Local
public interface IAutorService {
    
    public List<AutorP> listar(int estado);
    
    public Autor listarPorId(Integer id) throws ObjectNotFoundException;
    
    public AutorP listarPorIdA(Integer id, int estado) throws ObjectNotFoundException;
            
    public void guardar(Autor autor);
    
    public void editar(Autor autor);
   
    public void eliminar(Integer id) throws  ObjectNotFoundException;
    
    public void eliminarOpcion2(Integer id) throws ObjectNotFoundException;
    
}
