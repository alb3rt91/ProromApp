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

public class Rectangular extends Fragment {

    // Campos de entrada y resultados
    private EditText etNombreProyecto, etValorM, etValorCapitalM, etValorH, etValorCapitalH;
    private TextView tvResultadoSolera, tvResultadoMuros, tvResultadoTotalSuperficie, tvResultadoPerimetroPiscina, tvResultadoMediacanas;
    private SharedViewModel sharedViewModel;

    public Rectangular() {
        super(R.layout.fragment_rectangular); // Asocia el layout al fragmento.
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicialización de vistas.
        etNombreProyecto = view.findViewById(R.id.etNombreProyecto);
        etValorM = view.findViewById(R.id.etValorM);
        etValorCapitalM = view.findViewById(R.id.etValorCapitalM);
        etValorH = view.findViewById(R.id.etValorH);
        etValorCapitalH = view.findViewById(R.id.etValorCapitalH);

        tvResultadoSolera = view.findViewById(R.id.tvResultadoSolera);
        tvResultadoMuros = view.findViewById(R.id.tvResultadoMuros);
        tvResultadoTotalSuperficie = view.findViewById(R.id.tvResultadoTotalSuperficie);
        tvResultadoPerimetroPiscina = view.findViewById(R.id.tvResultadoPerimetroPiscina);
        tvResultadoMediacanas = view.findViewById(R.id.tvResultadoMediacanas);

        // Configura el cálculo automático al cambiar el texto.
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                calcularResultados();
            }
        };

        // Asigna el TextWatcher a los campos relevantes.
        etValorM.addTextChangedListener(textWatcher);
        etValorCapitalM.addTextChangedListener(textWatcher);
        etValorH.addTextChangedListener(textWatcher);
        etValorCapitalH.addTextChangedListener(textWatcher);

        // Inicializa el ViewModel compartido.
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Configura el botón de guardar.
        Button btnGuardar = view.findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(this::guardarProyecto);
    }

    // Calcula los resultados con base en los valores ingresados.
    private void calcularResultados() {
        double m = obtenerValorDeCampo(etValorM);
        double M = obtenerValorDeCampo(etValorCapitalM);
        double h = obtenerValorDeCampo(etValorH);
        double H = obtenerValorDeCampo(etValorCapitalH);

        double solera = m * M;
        double muros = (M * (h + H)) + ((m - 2) * h);
        double totalSuperficie = solera + muros;
        double perimetro = 2 * m + 2 * M;
        double mediacanas = m + M + M + h + H + h + H;

        // Actualiza los resultados en los TextViews.
        tvResultadoSolera.setText(String.format("Solera: %.2f m²", solera));
        tvResultadoMuros.setText(String.format("Muros: %.2f m²", muros));
        tvResultadoTotalSuperficie.setText(String.format("Total Superficie: %.2f m²", totalSuperficie));
        tvResultadoPerimetroPiscina.setText(String.format("Perímetro: %.2f ml", perimetro));
        tvResultadoMediacanas.setText(String.format("Mediacanas: %.2f ml", mediacanas));
    }

    // Obtiene el valor numérico de un campo de entrada.
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

    // Guarda el proyecto en el ViewModel compartido y navega de regreso.
    private void guardarProyecto(View view) {
        String nombreProyecto = etNombreProyecto.getText().toString().trim();
        if (nombreProyecto.isEmpty()) {
            Toast.makeText(requireContext(), "El nombre del proyecto no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crea los detalles del proyecto.
        String detalles = String.format(
                " %s\n %s\n %s\n %s\n %s",
                tvResultadoSolera.getText().toString(),
                tvResultadoMuros.getText().toString(),
                tvResultadoTotalSuperficie.getText().toString(),
                tvResultadoPerimetroPiscina.getText().toString(),
                tvResultadoMediacanas.getText().toString()
        );

        // Agrega el proyecto al ViewModel.
        Project proyecto = new Project(nombreProyecto, detalles);
        sharedViewModel.addProject(proyecto);

        Toast.makeText(requireContext(), "Proyecto guardado exitosamente", Toast.LENGTH_SHORT).show();

        // Limpia los campos de entrada.
        etNombreProyecto.setText("");
        etValorM.setText("");
        etValorCapitalM.setText("");
        etValorH.setText("");
        etValorCapitalH.setText("");

        // Navega de regreso al fragmento anterior.
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_rectangular_to_bottom1Fragment);
    }
}
