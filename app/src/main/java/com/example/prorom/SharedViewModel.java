package com.example.prorom;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<List<Project>> projects = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Project>> getProjects() {
        return projects;
    }

    public void addProject(Project project) {
        List<Project> currentProjects = projects.getValue();
        if (currentProjects != null) {
            currentProjects.add(project);
            projects.setValue(currentProjects);
        }
    }
    public void removeProject(Project project) {
        List<Project> currentProjects = projects.getValue();
        if (currentProjects != null) {
            currentProjects.remove(project);
            projects.setValue(currentProjects); // Actualizar LiveData
        }
    }
}