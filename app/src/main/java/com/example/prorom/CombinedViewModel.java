package com.example.prorom;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class CombinedViewModel extends ViewModel {

    public LiveData<List<BaseProject>> getCombinedProjects(SharedViewModel sharedViewModel,
                                                           RectangularRectangularViewModel rectangularViewModel,
                                                           RectangularRomanaViewModel rectangularRomanaViewModel) {
        return Transformations.map(sharedViewModel.getProjects(), sharedProjects -> {
            List<BaseProject> allProjects = new ArrayList<>(sharedProjects);
            if (rectangularViewModel.getProjects().getValue() != null) {
                allProjects.addAll(rectangularViewModel.getProjects().getValue());
            }
            if (rectangularRomanaViewModel.getProjects().getValue() != null) {
                allProjects.addAll(rectangularRomanaViewModel.getProjects().getValue());
            }
            return allProjects;
        });
    }
}
