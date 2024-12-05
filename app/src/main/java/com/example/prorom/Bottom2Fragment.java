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

/**
 * Fragmento para gestionar la lista combinada de proyectos.
 */
public class Bottom2Fragment extends Fragment {

    private RecyclerView recyclerView;
    private CombinedAdapter combinedAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Infla el diseño del fragmento.
        return inflater.inflate(R.layout.fragment_bottom2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configura el RecyclerView con un adaptador y un diseño lineal.
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        combinedAdapter = new CombinedAdapter();
        recyclerView.setAdapter(combinedAdapter);

        // Obtiene los ViewModels necesarios para combinar datos.
        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        RectangularRectangularViewModel rectangularViewModel = new ViewModelProvider(requireActivity()).get(RectangularRectangularViewModel.class);
        RectangularRomanaViewModel rectangularRomanaViewModel = new ViewModelProvider(requireActivity()).get(RectangularRomanaViewModel.class);
        CombinedViewModel combinedViewModel = new ViewModelProvider(this).get(CombinedViewModel.class);

        // Observa los proyectos combinados y actualiza el adaptador.
        combinedViewModel.getCombinedProjects(sharedViewModel, rectangularViewModel, rectangularRomanaViewModel)
                .observe(getViewLifecycleOwner(), combinedAdapter::setProjects);

        // Configura el gesto de deslizar para eliminar elementos.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false; // No se permite mover elementos.
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                BaseProject projectToRemove = combinedAdapter.getProjectAt(position);

                // Muestra un cuadro de diálogo para confirmar la eliminación.
                new AlertDialog.Builder(requireContext())
                        .setTitle("Eliminar Proyecto")
                        .setMessage("¿Estás seguro de que deseas eliminar este proyecto?")
                        .setPositiveButton("Eliminar", (dialog, which) -> {
                            // Elimina el proyecto del ViewModel correspondiente.
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
