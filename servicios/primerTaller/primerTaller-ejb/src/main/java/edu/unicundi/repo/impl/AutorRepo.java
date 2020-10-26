/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.repo.impl;

import edu.unicundi.entity.Autor;
import edu.unicundi.entity.Libro;
import edu.unicundi.repo.IAutorRepo;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Valentina
 */
@Stateless
public class AutorRepo implements IAutorRepo {

    @PersistenceContext(unitName = "edu.unicundi_primerTaller-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager entity;

    @Override
    public List<Autor> listar() {
        TypedQuery<Autor> listaAutor = this.entity.createNamedQuery("Autor.listarTodo", Autor.class);
        
        return listaAutor.getResultList();        
    }

    @Override
    public Autor listarPorId(Integer id) {
        return this.entity.find(Autor.class, id);
    }
    
    @Override
    public Autor listarPorIdA(Integer id) {
        Autor listaAutor =  this.entity.find(Autor.class, id);
        return listaAutor;
    }

    @Override
    public void guardar(Autor autor) {
        this.entity.persist(autor);
    }

    @Override
    public void editar(Autor autor) {
        this.entity.merge(autor);
    }

    @Override
    public void eliminar(Autor autor) {
        this.entity.remove(autor);
    }

}
