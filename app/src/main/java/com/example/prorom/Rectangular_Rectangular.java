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

public class Rectangular_Rectangular extends Fragment {

    // Campos de entrada
    private EditText etNombreProyectoRectangular, etValorMRectangular, etValorCapitalMRectangular, etValorHRectangular, etValorCapitalHRectangular,
            etValorEscalera1Rectangular, etValorEscalera2Rectangular;

    private RectangularRectangularViewModel rectangularRectangularViewModelViewModel;

    // Resultados
    private TextView tvResultadoSoleraRectangular, tvResultadoMurosRectangular, tvResultadoMurosYSoleraRectangular,tvResultadoTotalEscaleraRectangular,
            tvResultadoTotalPiscinaRectangular, tvResultadoPerimetroPiscinaRectangular, tvResultadoMediacanasRectangular;

    public Rectangular_Rectangular() {
        super(R.layout.fragment_rectangular__rectangular);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializar los campos de entrada
        etNombreProyectoRectangular = view.findViewById(R.id.etNombreProyectoRectangular);
        etValorMRectangular = view.findViewById(R.id.etValorMRectangular);
        etValorCapitalMRectangular = view.findViewById(R.id.etValorCapitalMRectangular);
        etValorHRectangular = view.findViewById(R.id.etValorHRectangular);
        etValorCapitalHRectangular = view.findViewById(R.id.etValorCapitalHRectangular);
        etValorEscalera1Rectangular = view.findViewById(R.id.etValorEscalera1Rectangular);
        etValorEscalera2Rectangular = view.findViewById(R.id.etValorEscalera2Rectangular);

        // Inicializar los TextViews para los resultados
        tvResultadoSoleraRectangular = view.findViewById(R.id.tvResultadoSoleraRectangular);
        tvResultadoMurosRectangular = view.findViewById(R.id.tvResultadoMurosRectangular);
        tvResultadoMurosYSoleraRectangular = view.findViewById(R.id.tvResultadoMurosYSoleraRectangular);
        tvResultadoTotalEscaleraRectangular = view.findViewById(R.id.tvResultadoEscaleraTotalRectangular);
        tvResultadoTotalPiscinaRectangular = view.findViewById(R.id.tvResultadoTotalPiscinaRectangular);
        tvResultadoPerimetroPiscinaRectangular = view.findViewById(R.id.tvResultadoPerimetroPiscinaRectangular);
        tvResultadoMediacanasRectangular = view.findViewById(R.id.tvResultadoMediacanasRectangular);

        // Añadir TextWatchers para calcular los resultados automáticamente
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

        etValorMRectangular.addTextChangedListener(textWatcher);
        etValorCapitalMRectangular.addTextChangedListener(textWatcher);
        etValorHRectangular.addTextChangedListener(textWatcher);
        etValorCapitalHRectangular.addTextChangedListener(textWatcher);
        etValorEscalera1Rectangular.addTextChangedListener(textWatcher);
        etValorEscalera2Rectangular.addTextChangedListener(textWatcher);

        rectangularRectangularViewModelViewModel= new ViewModelProvider(requireActivity()).get(RectangularRectangularViewModel.class);

        Button btnGuardar = view.findViewById(R.id.btnGuardarRectangular);
        btnGuardar.setOnClickListener(v -> guardarProyecto(v));
    }

    private void calcularResultados() {
        // Obtener los valores de los campos
        double m = obtenerValorDeCampo(etValorMRectangular);
        double M = obtenerValorDeCampo(etValorCapitalMRectangular);
        double h = obtenerValorDeCampo(etValorHRectangular);
        double H = obtenerValorDeCampo(etValorCapitalHRectangular);
        double l = obtenerValorDeCampo(etValorEscalera1Rectangular);
        double L = obtenerValorDeCampo(etValorEscalera2Rectangular);

        // Calcular resultados
        double solera = m * M;
        double muros = M * (h + H) + H * m + (m - L) * h;
        double soleraYMuros = solera + muros;
        double soleraEscalera = l * L;
        double murosEscalera = (l + l + L) * h;
        double totalEscalera = soleraEscalera + murosEscalera;
        double totalPiscina = soleraYMuros + totalEscalera;
        double perimetro = 2 * m + 2 * M + 2 * l;
        double mediacanas = 2 * (M + m) + 2 * (h + H);

        // Mostrar resultados en los TextViews
        tvResultadoSoleraRectangular.setText(String.format("Solera: %.2f m²", solera));
        tvResultadoMurosRectangular.setText(String.format("Muros: %.2f m²", muros));
        tvResultadoMurosYSoleraRectangular.setText(String.format("Solera y Muros: %.2f m²", soleraYMuros));
        tvResultadoTotalEscaleraRectangular.setText(String.format("Total Escalera: %.2f m²", totalEscalera));
        tvResultadoTotalPiscinaRectangular.setText(String.format("Total Piscina: %.2f m²", totalPiscina));
        tvResultadoPerimetroPiscinaRectangular.setText(String.format("Perímetro: %.2f ml", perimetro));
        tvResultadoMediacanasRectangular.setText(String.format("Mediacañas: %.2f ml", mediacanas));
    }

    private double obtenerValorDeCampo(EditText campo) {
        String texto = campo.getText().toString().trim();
        if (texto.isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            return 0.0; // Si el texto no es un número válido, devolvemos 0
        }
    }

    private void guardarProyecto(View view) {
        String nombreProyecto = etNombreProyectoRectangular.getText().toString().trim();
        if (nombreProyecto.isEmpty()) {
            Toast.makeText(requireContext(), "El nombre del proyecto no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        // Preparar detalles
        String detallesRectangular = String.format(
                " %s\n %s\n  %s\n %s\n %s\n %s\n %s\n",
                tvResultadoSoleraRectangular.getText().toString(),
                tvResultadoMurosRectangular.getText().toString(),
                tvResultadoMurosYSoleraRectangular.getText().toString(),
                tvResultadoTotalEscaleraRectangular.getText().toString(),
                tvResultadoTotalPiscinaRectangular.getText().toString(),
                tvResultadoPerimetroPiscinaRectangular.getText().toString(),
                tvResultadoMediacanasRectangular.getText().toString()
        );

        // Crear un nuevo proyecto y agregarlo al ViewModel
        RectangularRectangularProject proyecto = new RectangularRectangularProject(nombreProyecto, detallesRectangular);
        rectangularRectangularViewModelViewModel.addProject(proyecto);

        Toast.makeText(requireContext(), "Proyecto guardado exitosamente", Toast.LENGTH_SHORT).show();

        // Limpiar los campos
        etNombreProyectoRectangular.setText("");
        etValorMRectangular.setText("");
        etValorCapitalMRectangular.setText("");
        etValorHRectangular.setText("");
        etValorCapitalHRectangular.setText("");
        etValorEscalera1Rectangular.setText("");
        etValorEscalera2Rectangular.setText("");

        // Navegar de vuelta al bottom1Fragment
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_rectangular_Rectangular_to_bottom1Fragment);

    }
}