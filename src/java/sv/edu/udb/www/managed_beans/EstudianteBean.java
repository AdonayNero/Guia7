/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sv.edu.udb.www.entities.EstudianteEntity;
import sv.edu.udb.www.model.EstudiantesModel;
import sv.edu.udb.www.utils.JsfUtil;


/**
 *
 * @author Adonay
 */
@ManagedBean
@RequestScoped
public class EstudianteBean {

    /**
     * Creates a new instance of EstudianteBean
     */
    EstudiantesModel modelo = new EstudiantesModel();
    private EstudianteEntity estudiante;
    private List<EstudianteEntity> listaEstudiantes;

    public EstudianteBean() {
        estudiante = new EstudianteEntity();
    }

    public EstudianteEntity getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteEntity estudiante) {
        this.estudiante = estudiante;
    }

    public List<EstudianteEntity> getListaEstudiantes() {
        /* Notese que se llama al método listarEstudiantes
           para obtener la lista de objetos a partir de la bd */
        return modelo.listarEstudiante();
    }


    public String guardarEstudiante() {
        if (modelo.insertarEstudiante(estudiante) != 1) {
            JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
            //Forzando la redirección en el cliente
            return "registroEstudiantes?faces-redirect=true";
        }
    }
    
    
    
    public String eliminarEstudiante() {
        // Leyendo el parametro enviado desde la vista
        String carnet= JsfUtil.getRequest().getParameter("carnet");
        
        if (modelo.eliminarEstudiante(carnet) > 0) {
            JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        }
        else{
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return "registroEstudiantes?faces-redirect=true";

    }


}


