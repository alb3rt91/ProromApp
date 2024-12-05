package com.example.prorom;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 * ViewModel compartido para gestionar una lista de proyectos estándar.
 */
public class SharedViewModel extends ViewModel {

    // LiveData para almacenar y observar los proyectos.
    private final MutableLiveData<List<Project>> projects = new MutableLiveData<>(new ArrayList<>());

    /**
     * Obtiene los proyectos como LiveData para su observación.
     *
     * @return LiveData con la lista de proyectos.
     */
    public LiveData<List<Project>> getProjects() {
        return projects;
    }

    /**
     * Añade un nuevo proyecto a la lista y actualiza el LiveData.
     *
     * @param project Proyecto a añadir.
     */
    public void addProject(Project project) {
        List<Project> currentProjects = projects.getValue();
        if (currentProjects != null) {
            currentProjects.add(project); // Añadir el proyecto a la lista.
            projects.setValue(currentProjects); // Actualizar LiveData.
        }
    }

    /**
     * Elimina un proyecto de la lista y actualiza el LiveData.
     *
     * @param project Proyecto a eliminar.
     */
    public void removeProject(Project project) {
        List<Project> currentProjects = projects.getValue();
        if (currentProjects != null) {
            currentProjects.remove(project); // Eliminar el proyecto de la lista.
            projects.setValue(currentProjects); // Actualizar LiveData.
        }
    }
}
