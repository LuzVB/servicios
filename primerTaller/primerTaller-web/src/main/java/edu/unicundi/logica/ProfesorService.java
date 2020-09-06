/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.logica;
import edu.unicundi.controller.pojo.Materia;
import edu.unicundi.controller.pojo.Profesor;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Valentina
 */
public class ProfesorService extends Datos implements Serializable  {
    
    private List<Profesor> listaProfesores;

    public ProfesorService() {
    }
    
    public void listarProfesor() throws SQLException {
        
        listaProfesores = new ArrayList<>();
        List<Materia> listaMaterias = new ArrayList<>();
        listaMaterias.add(new Materia(1,"Matematicas",15,2));
        java.sql.Statement st = conexion.createStatement();
        System.out.println("AQUIII "+listaMaterias);
        try {
            String sql = "SELECT id_profesor, cedula_profesor, nombre_profesor, apellido_profesor, correo_profesor, edad_correo\n" +"FROM public.profesor;";
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                int id = Integer.parseInt(result.getString("id_profesor"));
                int edad = Integer.parseInt(result.getString("edad_correo"));
                int cedula = Integer.parseInt(result.getString("cedula_profesor"));
                listaProfesores.add(new Profesor(id,edad ,cedula , result.getString("nombre_profesor"), result.getString("apellido_profesor"), result.getString("correo_profesor"),listaMaterias));    
            }
            
            System.out.println("AQUIII2 "+listaProfesores);
           
        } catch (Exception e) {

        }
    }

    public List<Profesor> getListaProfesores() {
        return listaProfesores;
    }

    public void setListaProfesores(List<Profesor> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }
    
}
