/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.controller.pojo;

/**
 *
 * @author Luz
 */
public class Materia {
    
    private Integer id;
    private String nombre;
    private Integer cupo;
    private Integer credito;

    public Materia(){
    }
    
    public Materia(Integer id,String nombre,Integer cupo, Integer credito){
        this.id = id;
        this.nombre = nombre;
        this.cupo = cupo;
        this.credito = credito;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCupo() {
        return cupo;
    }

    public void setCupo(Integer cupo) {
        this.cupo = cupo;
    }

    public Integer getCredito() {
        return credito;
    }

    public void setCredito(Integer credito) {
        this.credito = credito;
    }
    
  
}
