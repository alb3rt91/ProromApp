package com.example.prorom;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Bottom2Fragment extends Fragment {

    private RecyclerView recyclerView;
    private CombinedAdapter combinedAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        combinedAdapter = new CombinedAdapter();
        recyclerView.setAdapter(combinedAdapter);

        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        RectangularRectangularViewModel rectangularViewModel = new ViewModelProvider(requireActivity()).get(RectangularRectangularViewModel.class);
        RectangularRomanaViewModel rectangularRomanaViewModel = new ViewModelProvider(requireActivity()).get(RectangularRomanaViewModel.class);
        CombinedViewModel combinedViewModel = new ViewModelProvider(this).get(CombinedViewModel.class);

        combinedViewModel.getCombinedProjects(sharedViewModel, rectangularViewModel, rectangularRomanaViewModel)
                .observe(getViewLifecycleOwner(), combinedAdapter::setProjects);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                BaseProject projectToRemove = combinedAdapter.getProjectAt(position);

                // Mostrar cuadro de confirmación
                new AlertDialog.Builder(requireContext())
                        .setTitle("Eliminar Proyecto")
                        .setMessage("¿Estás seguro de que deseas eliminar este proyecto?")
                        .setPositiveButton("Eliminar", (dialog, which) -> {
                            // Identificar y eliminar del ViewModel correspondiente
                            if (projectToRemove instanceof Project) {
                                sharedViewModel.removeProject((Project) projectToRemove);
                            } else if (projectToRemove instanceof RectangularRectangularProject) {
                                rectangularViewModel.removeProject((RectangularRectangularProject) projectToRemove);
                            } else if (projectToRemove instanceof RectangularRomanaProject) {
                                rectangularRomanaViewModel.removeProject((RectangularRomanaProject) projectToRemove);
                            }
                        })
                        .setNegativeButton("Cancelar", (dialog, which) -> combinedAdapter.notifyItemChanged(position))
                        .show();
            }
        }).attachToRecyclerView(recyclerView);
    }
}