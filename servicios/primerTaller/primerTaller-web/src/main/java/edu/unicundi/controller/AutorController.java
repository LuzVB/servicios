/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.controller;

import edu.unicundi.dto.AutorP;
import edu.unicundi.entity.Autor;
import edu.unicundi.exception.ObjectNotFoundException;
import edu.unicundi.exception.ParamRequiredException;
import edu.unicundi.exception.ParamUsedException;
import edu.unicundi.service.IAutorService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Valentina
 */
@Stateless
@Path("/autores")
public class AutorController {
    
    @EJB
    private IAutorService service;
    
    @Path("/listarA/{estado}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listr(@PathParam("estado" ) int estado)  {
        List<AutorP> listarAutor = service.listar(estado);
        return Response.status(Response.Status.OK).entity(listarAutor).build();       
    }  
    
    @Path("/listarOpcion1/{estado}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@PathParam("estado" ) boolean estado)  {
        List<AutorP> listarAutor = service.listarOpcion1(estado);
        return Response.status(Response.Status.OK).entity(listarAutor).build();       
    }        

    @Path("/listarOpcion2/{estado}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarOpcion2(@PathParam("estado" ) boolean estado)  {
        List<AutorP> listarAutor = service.listarOpcion2(estado);
        return Response.status(Response.Status.OK).entity(listarAutor).build();       
    }        

    @Path("/listarOpcion3/{estado}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarOpcion3(@PathParam("estado" ) boolean estado)  {
        List<Autor> listarAutor = service.listarOpcion3(estado);
        return Response.status(Response.Status.OK).entity(listarAutor).build();       
    }     
    
    @Path("/retornarPorIdA/{id}/{estado}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornarPorId(@PathParam("id" ) Integer id ,@PathParam("estado")int estado) throws ObjectNotFoundException {
        AutorP autor = service.listarPorIdA(id,estado);
        return Response.status(Response.Status.OK).entity(autor).build();       
    }      

   
    @Path("/bloquear/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response bloquear(@PathParam("id" ) Integer id) throws ObjectNotFoundException, ParamUsedException {
        service.bloquearAutor(id);
        return Response.status(Response.Status.OK).build();       
    } 
    
    @Path("/habilitar/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response habilitar(@PathParam("id" ) Integer id) throws ObjectNotFoundException, ParamUsedException {
        service.habilitarAutor(id);
        return Response.status(Response.Status.OK).build();       
    } 
    
    
    @Path("/guardar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardar(@Valid Autor autor) {
        service.guardar(autor);
        return Response.status(Response.Status.CREATED).build();
    }
    
    @Path("/editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@Valid Autor autor) throws ParamRequiredException, ObjectNotFoundException {
        service.editar(autor);
        return Response.status(Response.Status.OK).build();
    }
    
    @Path("/eliminarA/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id" ) Integer id) throws ObjectNotFoundException {
        service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();       
    }   
    
    @Path("/eliminarA2/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarOpcion2(@PathParam("id" ) Integer id) throws ObjectNotFoundException {
        service.eliminarOpcion2(id);
        return Response.status(Response.Status.NO_CONTENT).build();       
    }
    
}
