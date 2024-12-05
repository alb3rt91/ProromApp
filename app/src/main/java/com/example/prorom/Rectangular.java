package com.example.prorom;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

public class Rectangular extends Fragment {

    private EditText etNombreProyecto, etValorM, etValorCapitalM, etValorH, etValorCapitalH;
    private TextView tvResultadoSolera, tvResultadoMuros, tvResultadoTotalSuperficie, tvResultadoPerimetroPiscina, tvResultadoMediacanas;
    private SharedViewModel sharedViewModel;

    public Rectangular() {
        super(R.layout.fragment_rectangular);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializar los campos de entrada
        etNombreProyecto = view.findViewById(R.id.etNombreProyecto);
        etValorM = view.findViewById(R.id.etValorM);
        etValorCapitalM = view.findViewById(R.id.etValorCapitalM);
        etValorH = view.findViewById(R.id.etValorH);
        etValorCapitalH = view.findViewById(R.id.etValorCapitalH);

        // Inicializar los TextViews para los resultados
        tvResultadoSolera = view.findViewById(R.id.tvResultadoSolera);
        tvResultadoMuros = view.findViewById(R.id.tvResultadoMuros);
        tvResultadoTotalSuperficie = view.findViewById(R.id.tvResultadoTotalSuperficie);
        tvResultadoPerimetroPiscina = view.findViewById(R.id.tvResultadoPerimetroPiscina);
        tvResultadoMediacanas = view.findViewById(R.id.tvResultadoMediacanas);

        // Añadir TextWatchers a los campos de entrada
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                calcularResultados();
            }
        };

        etValorM.addTextChangedListener(textWatcher);
        etValorCapitalM.addTextChangedListener(textWatcher);
        etValorH.addTextChangedListener(textWatcher);
        etValorCapitalH.addTextChangedListener(textWatcher);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        Button btnGuardar = view.findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(v -> guardarProyecto(v));

    }

    private void calcularResultados() {
        double m = obtenerValorDeCampo(etValorM);
        double M = obtenerValorDeCampo(etValorCapitalM);
        double h = obtenerValorDeCampo(etValorH);
        double H = obtenerValorDeCampo(etValorCapitalH);

        // Operaciones
        double solera = m * M;
        double muros = (M * (h + H)) + ((m - 2) * h);
        double totalSuperficie = solera + muros;
        double perimetro = 2 * m + 2 * M;
        double mediacanas = m + M + M + h + H + h + H;

        // Actualizar los TextViews
        tvResultadoSolera.setText(String.format("Solera: %.2f m²", solera));
        tvResultadoMuros.setText(String.format("Muros: %.2f m²", muros));
        tvResultadoTotalSuperficie.setText(String.format("Total Superficie: %.2f m²", totalSuperficie));
        tvResultadoPerimetroPiscina.setText(String.format("Perímetro: %.2f ml", perimetro));
        tvResultadoMediacanas.setText(String.format("Mediacanas: %.2f ml", mediacanas));
    }

    private double obtenerValorDeCampo(EditText campo) {
        String texto = campo.getText().toString().trim();
        if (texto.isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private void guardarProyecto(View view) {
        String nombreProyecto = etNombreProyecto.getText().toString().trim();
        if (nombreProyecto.isEmpty()) {
            Toast.makeText(requireContext(), "El nombre del proyecto no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        // Preparar detalles
        String detalles = String.format(
                " %s\n %s\n %s\n %s\n %s",
                tvResultadoSolera.getText().toString(),
                tvResultadoMuros.getText().toString(),
                tvResultadoTotalSuperficie.getText().toString(),
                tvResultadoPerimetroPiscina.getText().toString(),
                tvResultadoMediacanas.getText().toString()
        );

        // Crear un nuevo proyecto y agregarlo al ViewModel
        Project proyecto = new Project(nombreProyecto, detalles);
        sharedViewModel.addProject(proyecto);

        Toast.makeText(requireContext(), "Proyecto guardado exitosamente", Toast.LENGTH_SHORT).show();

        // Limpiar los campos
        etNombreProyecto.setText("");
        etValorM.setText("");
        etValorCapitalM.setText("");
        etValorH.setText("");
        etValorCapitalH.setText("");

        // Navegar de vuelta al bottom1Fragment
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_rectangular_to_bottom1Fragment);
    }
}
