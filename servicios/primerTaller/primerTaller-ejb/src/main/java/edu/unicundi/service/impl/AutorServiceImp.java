/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service.impl;

import edu.unicundi.dto.AutorP;
import edu.unicundi.entity.Autor;
import edu.unicundi.entity.Libro;
import edu.unicundi.exception.ObjectNotFoundException;
import edu.unicundi.repo.IAutorRepo;
import edu.unicundi.service.IAutorService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Valentina
 */
@Stateless
public class AutorServiceImp implements IAutorService {

    @EJB
    private IAutorRepo repo;

    @Override
    public List<AutorP> listar(int estado) {
        List<AutorP> autor = new ArrayList<>();
        List<Autor> autorLista = repo.listar();
        for (Autor a : autorLista) {
            List<Libro> libro = new ArrayList<>();
            AutorP AutorPojo = new AutorP();
            AutorPojo.setId(a.getId());
            AutorPojo.setApellido(a.getApellido());
            AutorPojo.setFecha(a.getFecha());
            AutorPojo.setNombre(a.getNombre());
            for (Libro libros : a.getLibro()) {
                Libro l = new Libro();
                l.setId(libros.getId());
                l.setNombre(libros.getNombre());
                l.setAutor(libros.getAutor());
                l.setEditorial(libros.getEditorial());
                libro.add(l);
            }
            if (estado == 1) {
                AutorPojo.setLibro(libro);
            }
            autor.add(AutorPojo);
        }

        return autor;
    }

    @Override
    public Autor listarPorId(Integer id) throws ObjectNotFoundException {
        Autor autor = repo.listarPorId(id);
        if (autor == null) {
            throw new ObjectNotFoundException("Autor no existe.");
        } else {
            return autor;
        }
    }
    
    @Override
    public AutorP listarPorIdA(Integer id, int estado) throws ObjectNotFoundException {
        Autor autor = repo.listarPorId(id);
        AutorP a = new AutorP();
        
        if (autor == null) {
            throw new ObjectNotFoundException("Autor no existe.");
        } else {
            a.setApellido(autor.getApellido());
            a.setNombre(autor.getNombre());
            a.setFecha(autor.getFecha());
            a.setId(autor.getId());
            if (estado == 1) {
                a.setLibro(autor.getLibro());
                return a;
            } else {
                return a;
            }
        }
    }

    @Override
    public void guardar(Autor autor) {
        if (autor.getLibro() != null) {
            for (Libro libro : autor.getLibro()) {
                libro.setAutor(autor);
            }
        }
        repo.guardar(autor);
    }

    @Override
    public void editar(Autor autor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Integer id) throws ObjectNotFoundException {
        Autor autor = this.listarPorId(id);
        repo.eliminar(autor);
    }
    
    @Override
    public void eliminarOpcion2(Integer id) throws ObjectNotFoundException {
        Autor autor = this.listarPorId(id);
        if(autor.getLibro().isEmpty() == true){
              repo.eliminar(autor);
        }else{
             throw new ObjectNotFoundException("El autor tiene asociados los libros(ELIMINE LOS LIBROS).");

        }
    }

}
