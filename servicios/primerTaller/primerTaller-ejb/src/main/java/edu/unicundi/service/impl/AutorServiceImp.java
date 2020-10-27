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
import edu.unicundi.exception.ParamRequiredException;
import edu.unicundi.exception.ParamUsedException;
import edu.unicundi.repo.IAutorRepo;
import edu.unicundi.service.IAutorService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;
/**
 *
 * @author Valentina
 */
@Stateless
public class AutorServiceImp implements IAutorService {

    @EJB
    private IAutorRepo repo;

    @Override
    public List<AutorP> listarOpcion1(boolean estado) {
        List<Autor> listaAutor = repo.listar();
        List<AutorP> listaAutorDto = new ArrayList<>();

        if (estado) {
            for (Autor lista : listaAutor) {
                ModelMapper modelMapper = new ModelMapper();
                AutorP autorDto = modelMapper.map(lista, AutorP.class);
                listaAutorDto.add(autorDto);
            }
            
            return listaAutorDto;
        } else {
            for (Autor lista : listaAutor) {
                ModelMapper modelMapper = new ModelMapper();
                AutorP autorDto = modelMapper.map(lista, AutorP.class);
                listaAutorDto.add(autorDto);
            }
            for (AutorP aut : listaAutorDto) {
                aut.setLibro(null);
            }
            
             return listaAutorDto;
        }
       
    }

    //REVISAR ESTE METODO
    @Override
    public List<AutorP> listarOpcion2(boolean estado) {
        List<Autor> listaAutor =repo.listarOpcion2();
        List<AutorP> listaAutorDto = new ArrayList<>();

        if (estado) {
            for (Autor lista : listaAutor) {
                ModelMapper modelMapper = new ModelMapper();
                AutorP autorDto = modelMapper.map(lista, AutorP.class);
                listaAutorDto.add(autorDto);
            }
           return listaAutorDto;
        }
        return listaAutorDto;
    }
    
    @Override
    public List<Autor> listarOpcion3(boolean estado) {
        
        List<Autor> listaAutor = repo.listarOpcion3();
        if (estado) {
            return listaAutor;
        } else {

            for (Autor lista : listaAutor) {
                lista.setLibro(null);
            }
        }
        return listaAutor;
    }
    
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
            AutorPojo.setEstado(a.getEstado());
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
            a.setEstado(autor.getEstado());
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
    public void editar(Autor autor) throws ParamRequiredException, ObjectNotFoundException{
        if(autor.getId() == null)    
            throw new ParamRequiredException("Id es requerido para edición");
        else {
            Autor autorAux = repo.listarPorId(autor.getId());
            
            if(autor == null)
                throw new ObjectNotFoundException("Autor no existe.");
            
            autorAux.setApellido(autor.getApellido());
            autorAux.setNombre(autor.getNombre());
            autorAux.setFecha(autor.getFecha());
            
            //EVITAR ESTO
            //autorAux.setLibro(autor.getLibro());
            //autorAux.getLibro().get(0).setNombre("nombre");
            
            repo.editar(autorAux);
        }
        
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

    @Override
    public void bloquearAutor(Integer idAutor) throws ParamUsedException {
        this.validarId(idAutor);
        repo.bloquear(idAutor);
    }
    
    @Override
    public void habilitarAutor(Integer idAutor) throws ParamUsedException {
        this.validarId(idAutor);
        repo.habilitar(idAutor);
    }
    
    
     private void validarId(Integer idAutor) throws ParamUsedException{
            Integer validacion = repo.validaId(idAutor);
            if(validacion == 0)
                throw new ParamUsedException("El ID no se escuentra registrado");          
    } 
}
