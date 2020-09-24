/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import edu.unicundi.controller.pojo.ErrorWrraper;
import edu.unicundi.dto.Profesor;
import edu.unicundi.service.IProfesorService;
import java.sql.SQLException;
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
@Path("/profesores")
@Api(value = "/profesores", description = "Api para el manejo de la informacion de los profesores")
public class ProfesorController {

    @EJB
    public IProfesorService service;
   
    @Path("/retornarProfesor")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(produces = "application/json", value = "Retorna la lista de todos los profesores", consumes = "aplication/json",
            httpMethod = "GET")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    public Response retornarProfesor() throws SQLException {
        service.listarProfesor();
        return Response.status(Response.Status.OK).entity(service.getListaProfesores()).build();
    }

    @Path("/retornarProfesorCedula/{cedula}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(produces = "application/json", value = "Retorna la lista de datos del profesor por la cedula ", consumes = "aplication/json",
            httpMethod = "GET")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    public Response retornarEstudiantePorCedula(@PathParam("cedula") int cedula) {
        service.listarProfesorCedula(cedula);
        return Response.status(Response.Status.OK).entity(service.getListaProfesores()).build();
    }

    @Path("/retornarProfesorMateria/{materia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(produces = "application/json", value = "Retorna la lista de todos los profesores por la materia", consumes = "aplication/json",
            httpMethod = "GET")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    public Response retornarEstudiantePorMateria(@PathParam("materia") String materia) {
        service.listarProfesorMateria(materia);
        return Response.status(Response.Status.OK).entity(service.getListaProfesores()).build();
    }

    @Path("/editarProfesor")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(produces = "application/json", value = "Edita los datos del profesor", consumes = "aplication/json",
            httpMethod = "PUT")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 422, message = "Invalid data"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 405, message = "Method Not Allowed"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    public Response editar(Profesor profesor) throws SQLException {
        service.editarProfesor(profesor);
        return Response.status(Response.Status.OK).build();
    }

    @Path("/insertarProfesor")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(produces = "application/json", value = "Insertar un al profesor con su correspondientes datos", consumes = "aplication/json",
            httpMethod = "POST")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 422, message = "Invalid data"),
        @ApiResponse(code = 405, message = "Method Not Allowed"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    public Response insertarEstudiante(@Valid Profesor profesor) throws Exception {
        service.insertarProfesor(profesor);
        return Response.status(Response.Status.CREATED).build();
    }

    @Path("eliminar/{idProfesor}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(produces = "application/json", value = "Elimina un profesor de la BD", consumes = "aplication/json",
            httpMethod = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 405, message = "Method Not Allowed"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    public Response eliminar(@PathParam("idProfesor") int id) {
        service.eliminarProfesor(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
