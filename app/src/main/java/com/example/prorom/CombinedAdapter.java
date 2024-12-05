package com.example.prorom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinedAdapter extends RecyclerView.Adapter<CombinedAdapter.ProjectViewHolder> {

    private final List<BaseProject> projects = new ArrayList<>();
    private final Set<Integer> expandedItems = new HashSet<>(); // Rastrear ítems expandidos

    public void setProjects(List<BaseProject> projects) {
        this.projects.clear();
        this.projects.addAll(projects);
        notifyDataSetChanged();
    }

    public BaseProject getProjectAt(int position) {
        return projects.get(position);
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_proyecto, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        BaseProject project = projects.get(position);
        holder.tvName.setText(project.getName());

        // Mostrar u ocultar detalles según el estado
        if (expandedItems.contains(position)) {
            holder.tvDetails.setVisibility(View.VISIBLE);
            holder.tvDetails.setText(project.getDetails());
        } else {
            holder.tvDetails.setVisibility(View.GONE);
            holder.tvDetails.setText("");
        }

        // Configurar el click listener para expandir/colapsar detalles
        holder.itemView.setOnClickListener(v -> {
            if (expandedItems.contains(position)) {
                expandedItems.remove(position); // Colapsar
            } else {
                expandedItems.add(position); // Expandir
            }
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDetails;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvProjectName);
            tvDetails = itemView.findViewById(R.id.tvProjectDetails);
        }
    }
}
