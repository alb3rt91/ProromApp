package com.example.prorom;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class RectangularRomanaViewModel extends ViewModel {
    private final MutableLiveData<List<RectangularRomanaProject>> projects = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<RectangularRomanaProject>> getProjects() {
        return projects;
    }

    public void addProject(RectangularRomanaProject project) {
        List<RectangularRomanaProject> currentProjects = projects.getValue();
        if (currentProjects != null) {
            currentProjects.add(project);
            projects.setValue(currentProjects);
        }
    }
    public void removeProject(RectangularRomanaProject project) {
        List<RectangularRomanaProject> currentProjects = projects.getValue();
        if (currentProjects != null) {
            currentProjects.remove(project);
            projects.setValue(currentProjects); // Actualizar LiveData
        }
    }
}