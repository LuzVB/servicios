/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.logica;
import edu.unicundi.controller.pojo.Materia;
import edu.unicundi.controller.pojo.Profesor;
import edu.unicundi.logica.Datos;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
/**
 *
 * @author Valentina
 */
public class ProfesorService extends Datos implements Serializable  {
    
    private List<Profesor> listaProfesores;
    private List<Materia> listaMaterias;
    private int idProfesor;

    public ProfesorService() {
    }
    
    public void listarProfesor() throws SQLException {
        
        listaProfesores = new ArrayList<>();
        java.sql.Statement st = conexion.createStatement();
        System.out.println("AQUIII "+listaMaterias);
        try {
            String sql = "SELECT id_profesor, cedula_profesor, nombre_profesor, apellido_profesor, correo_profesor, edad_correo\n" +"FROM public.profesor;";
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                this.idProfesor= Integer.parseInt(result.getString("id_profesor"));
                int edad = Integer.parseInt(result.getString("edad_correo"));
                int cedula = Integer.parseInt(result.getString("cedula_profesor"));
                listaMateriasProfesor();
                listaProfesores.add(new Profesor(this.idProfesor,edad ,cedula , result.getString("nombre_profesor"), result.getString("apellido_profesor"), result.getString("correo_profesor"),this.getListaMaterias()));    
            }
           
        } catch (Exception e) {

        }
    }
    public void listarProfesorCedula(int cedula) throws SQLException {
        
        listaProfesores = new ArrayList<>();
        java.sql.Statement st = conexion.createStatement();
        try {
            String sql = "SELECT id_profesor, cedula_profesor, nombre_profesor, apellido_profesor, correo_profesor, edad_correo FROM public.profesor where cedula_profesor="+cedula+";";
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                this.idProfesor= Integer.parseInt(result.getString("id_profesor"));
                int edad = Integer.parseInt(result.getString("edad_correo"));
                int numeroCedula = Integer.parseInt(result.getString("cedula_profesor"));
                listaMateriasProfesor();
                listaProfesores.add(new Profesor(this.idProfesor,edad ,numeroCedula , result.getString("nombre_profesor"), result.getString("apellido_profesor"), result.getString("correo_profesor"),this.getListaMaterias()));    
            }
        } catch (Exception e) {

        }
    }
    public void listaMateriasProfesor() throws SQLException{
       
        listaMaterias = new ArrayList<>();
        java.sql.Statement st = conexion.createStatement();
        try {
                String sql2= "SELECT materia.id_materia,materia.nombre_materia,materia.cupos_materia,materia.creditos_materia FROM materia,profesor_materia where materia.id_materia=profesor_materia.id_materia and profesor_materia.id_profesor="+this.idProfesor+";";
                ResultSet result2 = st.executeQuery(sql2);
            while (result2.next()) {
                int idMateria= Integer.parseInt(result2.getString("id_materia"));
                int cuposMateria = Integer.parseInt(result2.getString("cupos_materia"));
                int creditosMateria =Integer.parseInt(result2.getString("creditos_materia"));
                listaMaterias.add(new Materia(idMateria,result2.getString("nombre_materia"),cuposMateria,creditosMateria));      
            }          
        } catch (Exception e) {

        }            
                
    }
    public void editatrProfesor(Profesor profesor) throws SQLException{
        
        this.idProfesor = Integer.parseInt(profesor.getId().toString());
        int edad = Integer.parseInt(profesor.getEdad().toString());
        int cedula = Integer.parseInt(profesor.getCedula().toString());
        System.out.println(profesor.getNombre() + " " + profesor.getApellido() + " " + profesor.getEdad());
        String cadenaSql = "UPDATE public.profesor SET  id_profesor='" + this.idProfesor + "',cedula_profesor='" + cedula + "',nombre_profesor='" + profesor.getNombre()+ "',apellido_profesor='" + profesor.getApellido() + "',correo_profesor='" + profesor.getCorreo() + "',edad_correo='" + edad + "' WHERE id_profesor=" + this.idProfesor + ";";
        FacesMessage message = new FacesMessage("Editó el profesor: " + profesor.getNombre());
        Datos conexion = new Datos();
        conexion.modifacionBaseDatos(cadenaSql, message);
 
                               
    }
    public List<Profesor> getListaProfesores() {
        return listaProfesores;
    }

    public void setListaProfesores(List<Profesor> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

    public List<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(List<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    
    
    
}
