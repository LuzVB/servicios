/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.controller.pojo;


import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Valentina
 */
@ApiModel("Modelo Profesor")
public class Profesor implements Serializable{
    
    @Id
    @NotNull 
    @ApiModelProperty(value = "id del profesro", required = true)
    private Integer id;
    
    @NotNull
    @ApiModelProperty(value = "edad del profesor", required = true)
    private Integer edad;
    
    @NotNull
    @ApiModelProperty(value = "cedula del profesor", required = true)
    private Integer cedula;
    
    @NotNull
    @ApiModelProperty(value = "nombre del profesor", required = true)
    private String nombre;
    
    @NotNull
    @ApiModelProperty(value = "apellido del profesor", required = true)
    private String apellido;
    
    @NotNull
    @ApiModelProperty(value = "correo del profesor", required = true)
    private String correo;
    
    @NotNull
    @ApiModelProperty(value = "lista de materias del profesor", required = true)
    private List<Materia> listaMateria;

    public Profesor() {
    }
    
    public Profesor(Integer id, Integer edad, Integer cedula, String nombre, String apellido, String correo, List<Materia> listaMateria )
    {
        this.id = id;
        this.edad = edad;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.listaMateria = listaMateria;
    }

    @ApiModelProperty(value = "Id del profesor")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @ApiModelProperty(value = "edad del profesor")
    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    @ApiModelProperty(value = "cedula del profesor")
    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }
    @ApiModelProperty(value = "nombre del profesor")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @ApiModelProperty(value = "apellido del profesor")
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    @ApiModelProperty(value = "correo del profesor")
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    @ApiModelProperty(value = "lista de materias del  profesor")
    public List<Materia> getListaMateria() {
        return listaMateria;
    }

    public void setListaMateria(List<Materia> listaMateria) {
        this.listaMateria = listaMateria;
    }
    
    
}
