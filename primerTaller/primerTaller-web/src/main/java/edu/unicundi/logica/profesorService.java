/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.logica;
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
public class profesorService extends Datos implements Serializable  {
    
    private List<Profesor> listaProfesores;

    public profesorService() {
    }
    
    public void listarProfesor() throws SQLException {
        
        listaProfesores = new ArrayList<>();
        listaProfesores.clear();
        java.sql.Statement st = conexion.createStatement();
        try {
            String sql = "SELECT id_profesor, cedula_profesor, nombre_profesor, apellido_profesor, correo_profesor, edad_correo FROM public.profesor";
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                int id = Integer.parseInt(result.getString("id_profesor"));
                int edad = Integer.parseInt(result.getString("edad_correo"));
                int cedula = Integer.parseInt(result.getString("cedula_profesor"));
                listaProfesores.add(new Profesor(id,edad ,cedula , result.getString("nombre_profesor"), result.getString("apellid_profesor"), result.getString("correo_profesor")));
                System.out.print(listaProfesores);    
            }
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
