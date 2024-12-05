package com.example.prorom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Bottom3Fragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bottom3, container, false);

        // Inicializa el RecyclerView
        recyclerView = rootView.findViewById(R.id.recyclerViewImages);

        // Configura el LayoutManager (en este caso, un GridLayoutManager)
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3); // 3 columnas
        recyclerView.setLayoutManager(layoutManager);

        // Asumiendo que tienes un array de imágenes de miniaturas (por ejemplo, imágenes locales)
        Integer[] imageThumbs = {
                R.drawable.image1, R.drawable.image2, R.drawable.image3,
                R.drawable.image4, R.drawable.image5, R.drawable.image6,
                R.drawable.image7, R.drawable.image8
        };

        // Configura el Adapter
        ImageAdapter adapter = new ImageAdapter(getActivity(), imageThumbs);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
