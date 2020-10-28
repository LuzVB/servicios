/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.controller;

import edu.unicundi.dto.LibroDto;
import edu.unicundi.entity.Curso;
import edu.unicundi.entity.Libro;
import edu.unicundi.service.ILibroService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Luz
 */
@Stateless
@Path("/libros")
public class LibroContoller {

    @EJB
    private ILibroService service;

    @Path("/listar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        List<LibroDto> listarLibro = service.listar();
        return Response.status(Response.Status.OK).entity(listarLibro).build();
    }
    
    @Path("/listar2")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar2() {
        List<Libro> listarLibro = service.listar2();
        return Response.status(Response.Status.OK).entity(listarLibro).build();
    }
    
    @Path("/guardar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardar(@Valid Libro libro) {
        service.guardar(libro);
        return Response.status(Response.Status.CREATED).build();
    }
}
