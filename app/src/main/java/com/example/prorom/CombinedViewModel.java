package com.example.prorom;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewModel para combinar proyectos de diferentes fuentes.
 */
public class CombinedViewModel extends ViewModel {

    /**
     * Combina proyectos de tres ViewModels diferentes en un único LiveData.
     *
     * @param sharedViewModel ViewModel con proyectos generales.
     * @param rectangularViewModel ViewModel con proyectos rectangulares.
     * @param rectangularRomanaViewModel ViewModel con proyectos rectangulares-romanos.
     * @return LiveData con la lista combinada de proyectos.
     */
    public LiveData<List<BaseProject>> getCombinedProjects(SharedViewModel sharedViewModel,
                                                           RectangularRectangularViewModel rectangularViewModel,
                                                           RectangularRomanaViewModel rectangularRomanaViewModel) {
        // Transforma los datos de sharedViewModel y combina con otros proyectos.
        return Transformations.map(sharedViewModel.getProjects(), sharedProjects -> {
            // Crea una lista combinada de todos los proyectos.
            List<BaseProject> allProjects = new ArrayList<>(sharedProjects);

            // Agrega los proyectos de RectangularRectangularViewModel si no son nulos.
            if (rectangularViewModel.getProjects().getValue() != null) {
                allProjects.addAll(rectangularViewModel.getProjects().getValue());
            }

            // Agrega los proyectos de RectangularRomanaViewModel si no son nulos.
            if (rectangularRomanaViewModel.getProjects().getValue() != null) {
                allProjects.addAll(rectangularRomanaViewModel.getProjects().getValue());
            }

            return allProjects;
        });
    }
}
