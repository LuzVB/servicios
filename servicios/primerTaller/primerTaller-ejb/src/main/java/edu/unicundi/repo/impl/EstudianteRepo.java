/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.repo.impl;

import edu.unicundi.entity.Estudiante;
import edu.unicundi.repo.IEstudianteRepo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Luz
 */
@Stateless
public class EstudianteRepo implements IEstudianteRepo {

    @PersistenceContext(unitName = "edu.unicundi_primerTaller-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager entity;

    @Override
    public List<Estudiante> listar() {
        TypedQuery<Estudiante> cursoList = this.entity.createNamedQuery("Estudiante.listarTodo", Estudiante.class);
        return cursoList.getResultList();
    }

    @Override
    public Estudiante listarPorId(Integer id) {
        Estudiante estudiante = this.entity.find(Estudiante.class, id);
        return estudiante;
    }

    @Override
    public void guardar(Estudiante estudiante) {
        this.entity.persist(estudiante);
    }

    @Override
    public void editar(Estudiante estudiante) {
        this.entity.merge(estudiante);
    }

    @Override
    public void eliminar(Estudiante estudiante) {
        this.entity.remove(estudiante);
    }
}
