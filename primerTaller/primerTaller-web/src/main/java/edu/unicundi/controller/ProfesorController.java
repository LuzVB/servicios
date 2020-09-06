/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.controller;

import edu.unicundi.controller.pojo.Profesor;
import edu.unicundi.controller.pojo.ConexionBD;
import edu.unicundi.logica.ProfesorService;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
@Path("/profesores")
public class ProfesorController {
        
    @Path("/retornarString")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornarString() {
        String nombre = "Johans González";
        return Response.status(Response.Status.OK).entity(nombre).build();
    }
    
    @Path("/retornarProfesor")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public  Response retornarProfesor() throws SQLException {
        ProfesorService estudiante = new ProfesorService();
        ConexionBD conexion = new ConexionBD();
        conexion.conectarBaseDatos();
        estudiante.listarProfesor();
        return Response.status(Response.Status.OK).entity(estudiante.getListaProfesores()).build();
    }  
    
    
}