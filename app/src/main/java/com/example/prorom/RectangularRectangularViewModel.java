package com.example.prorom;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class RectangularRectangularViewModel extends ViewModel {
    private final MutableLiveData<List<RectangularRectangularProject>> projects = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<RectangularRectangularProject>> getProjects() {
        return projects;
    }

    public void addProject(RectangularRectangularProject project) {
        List<RectangularRectangularProject> currentProjects = projects.getValue();
        if (currentProjects != null) {
            currentProjects.add(project);
            projects.setValue(currentProjects);
        }
    }

    public void removeProject(RectangularRectangularProject project) {
        List<RectangularRectangularProject> currentProjects = projects.getValue();
        if (currentProjects != null) {
            currentProjects.remove(project);
            projects.setValue(currentProjects); // Actualizar LiveData
        }
    }
}
