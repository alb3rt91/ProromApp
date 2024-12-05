package com.example.prorom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

/**
 * Fragmento para la parte inferior 1.
 */
public class Bottom1Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla el diseño del fragmento.
        return inflater.inflate(R.layout.fragment_bottom1, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configura el botón para navegar a otro fragmento.
        Button btnNuevoProyecto = view.findViewById(R.id.btnNuevoProyecto);
        btnNuevoProyecto.setOnClickListener(v ->
                NavHostFragment.findNavController(Bottom1Fragment.this)
                        .navigate(R.id.action_bottom1Fragment_to_tiposPiscinas)
        );
    }
}
