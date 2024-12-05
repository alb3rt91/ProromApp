package com.example.prorom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Adaptador para manejar una lista de proyectos combinados en un RecyclerView.
 */
public class CombinedAdapter extends RecyclerView.Adapter<CombinedAdapter.ProjectViewHolder> {

    private final List<BaseProject> projects = new ArrayList<>();
    private final Set<Integer> expandedItems = new HashSet<>(); // Rastrear ítems expandidos.

    /**
     * Establece la lista de proyectos y actualiza el RecyclerView.
     */
    public void setProjects(List<BaseProject> projects) {
        this.projects.clear();
        this.projects.addAll(projects);
        notifyDataSetChanged();
    }

    /**
     * Obtiene el proyecto en una posición específica.
     */
    public BaseProject getProjectAt(int position) {
        return projects.get(position);
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla la vista para cada ítem de la lista.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_proyecto, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        // Obtiene el proyecto actual y configura la vista.
        BaseProject project = projects.get(position);
        holder.tvName.setText(project.getName());

        // Expande o colapsa los detalles según el estado.
        if (expandedItems.contains(position)) {
            holder.tvDetails.setVisibility(View.VISIBLE);
            holder.tvDetails.setText(project.getDetails());
        } else {
            holder.tvDetails.setVisibility(View.GONE);
            holder.tvDetails.setText("");
        }

        // Configura el click listener para alternar entre expandir/colapsar.
        holder.itemView.setOnClickListener(v -> {
            if (expandedItems.contains(position)) {
                expandedItems.remove(position); // Colapsar.
            } else {
                expandedItems.add(position); // Expandir.
            }
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return projects.size(); // Devuelve el número de proyectos.
    }

    /**
     * ViewHolder para manejar las vistas de cada proyecto.
     */
    static class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDetails;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            // Vincula las vistas del layout.
            tvName = itemView.findViewById(R.id.tvProjectName);
            tvDetails = itemView.findViewById(R.id.tvProjectDetails);
        }
    }
}
