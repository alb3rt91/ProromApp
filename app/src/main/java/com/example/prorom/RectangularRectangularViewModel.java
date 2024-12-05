package com.example.prorom;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 * ViewModel para gestionar proyectos de piscinas rectangulares con escalera rectangular.
 */
public class RectangularRectangularViewModel extends ViewModel {

    // LiveData para almacenar y observar la lista de proyectos.
    private final MutableLiveData<List<RectangularRectangularProject>> projects = new MutableLiveData<>(new ArrayList<>());

    /**
     * Obtiene los proyectos como LiveData para que puedan ser observados.
     *
     * @return LiveData con la lista de proyectos.
     */
    public LiveData<List<RectangularRectangularProject>> getProjects() {
        return projects;
    }

    /**
     * Añade un nuevo proyecto a la lista y actualiza el LiveData.
     *
     * @param project Proyecto a añadir.
     */
    public void addProject(RectangularRectangularProject project) {
        List<RectangularRectangularProject> currentProjects = projects.getValue();
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
    public void removeProject(RectangularRectangularProject project) {
        List<RectangularRectangularProject> currentProjects = projects.getValue();
        if (currentProjects != null) {
            currentProjects.remove(project); // Eliminar el proyecto de la lista.
            projects.setValue(currentProjects); // Actualizar LiveData.
        }
    }
}
