/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service.impl;

import edu.unicundi.dto.LibroDto;
import edu.unicundi.entity.Libro;
import edu.unicundi.exception.ObjectNotFoundException;
import edu.unicundi.exception.ParamRequiredException;
import edu.unicundi.exception.ParamUsedException;
import edu.unicundi.repo.ILibroRepo;
import edu.unicundi.service.ILibroService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Luz
 */
@Stateless
public class LibroServiceImpl implements ILibroService {

    @EJB
    private ILibroRepo repo;

    @Override
    public List<LibroDto> listar() {
        List<Libro> listaLibro = repo.listar();
        List<LibroDto> libroDto = new ArrayList<>();

        for (Libro libro : listaLibro) {
            ModelMapper modelMapper = new ModelMapper();
            LibroDto dto = modelMapper.map(libro, LibroDto.class);
            libroDto.add(dto);
        }

        for (LibroDto dto : libroDto) {
            //dto.setAutor(null);
            dto.getAutor().getLibro().clear();
        }

        return libroDto;
    }

    @Override
    public List<Libro> listar2() {
        return repo.listar();
    }

    @Override
    public Libro listarPorId(Integer id) throws ObjectNotFoundException {
        Libro libro = repo.listarPorId(id);
        if (libro != null) {
            return libro;
        } else {
            throw new ObjectNotFoundException("El libro no existe.");
        }
    }

    @Override
    public void guardar(Libro libro) throws ParamRequiredException {
        if (libro.getAutor().getId() != null) {
            throw new ParamRequiredException("El Id del autor es reuerido");
//            for (Libro libro : autor.getLibro()) {
//                libro.setAutor(autor);
//            }
        }
//        repo.guardar(libro);
    }

    @Override
    public void editar(Libro curso) throws ParamRequiredException, ObjectNotFoundException, ParamUsedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Integer id) throws ObjectNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
