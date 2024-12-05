package com.example.prorom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

/**
 * Fragmento para seleccionar tipos de piscinas.
 */
public class TiposPiscinas extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla el diseño del fragmento.
        return inflater.inflate(R.layout.fragment_tipos_piscinas, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializar botones para seleccionar el tipo de piscina.
        Button btnPiscinaRectangular = view.findViewById(R.id.btnPiscinaRectangular);
        Button btnPiscinaEscaleraRomana = view.findViewById(R.id.btnPiscinaEscaleraRomana);
        Button btnPiscinaEscaleraRectangular = view.findViewById(R.id.btnPiscinaEscaleraRectangular);

        // Navegar al fragmento de piscina rectangular.
        btnPiscinaRectangular.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_tiposPiscinas_to_rectangular)
        );

        // Navegar al fragmento de piscina con escalera romana.
        btnPiscinaEscaleraRomana.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_tiposPiscinas_to_rectangular_Romana)
        );

        // Navegar al fragmento de piscina con escalera rectangular.
        btnPiscinaEscaleraRectangular.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_tiposPiscinas_to_rectangular_Rectangular)
        );
    }
}
