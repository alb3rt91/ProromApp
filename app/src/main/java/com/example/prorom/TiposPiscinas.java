package com.example.prorom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.prorom.R;

public class TiposPiscinas extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tipos_piscinas, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnPiscinaRectangular = view.findViewById(R.id.btnPiscinaRectangular);
        Button btnPiscinaEscaleraRomana = view.findViewById(R.id.btnPiscinaEscaleraRomana);
        Button btnPiscinaEscaleraRectangular = view.findViewById(R.id.btnPiscinaEscaleraRectangular);

        btnPiscinaRectangular.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_tiposPiscinas_to_rectangular)
        );

        btnPiscinaEscaleraRomana.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_tiposPiscinas_to_rectangular_Romana)
        );

        btnPiscinaEscaleraRectangular.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_tiposPiscinas_to_rectangular_Rectangular)
        );
    }
}
