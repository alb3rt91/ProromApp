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

/**
 * Fragmento para gestionar proyectos rectangulares con escalera romana.
 */
public class Rectangular_Romana extends Fragment {

    // Campos de entrada
    private EditText etNombreProyectoRomana, etValorMRomana, etValorCapitalMRomana, etValorHRomana, etValorCapitalHRomana, etValorEscalera;

    // Resultados
    private TextView tvResultadoSoleraRomana, tvResultadoMurosRomana, tvResultadoMurosYSoleraRomana,
            tvResultadoEscaleraTotal, tvResultadoTotalPiscina, tvResultadoPerimetroPiscinaRomana,
            tvResultadoMediacanasRomana, tvResultadoDiagonalRomana;

    private RectangularRomanaViewModel rectangularRomanaViewModel;

    public Rectangular_Romana() {
        super(R.layout.fragment_rectangular__romana); // Asociar layout al fragmento
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializar los campos de entrada
        etNombreProyectoRomana = view.findViewById(R.id.etNombreProyectoRomana);
        etValorMRomana = view.findViewById(R.id.etValorMRomana);
        etValorCapitalMRomana = view.findViewById(R.id.etValorCapitalMRomana);
        etValorHRomana = view.findViewById(R.id.etValorHRomana);
        etValorCapitalHRomana = view.findViewById(R.id.etValorCapitalHRomana);
        etValorEscalera = view.findViewById(R.id.etValorEscalera);

        // Inicializar los TextViews de resultados
        tvResultadoSoleraRomana = view.findViewById(R.id.tvResultadoSoleraRomana);
        tvResultadoMurosRomana = view.findViewById(R.id.tvResultadoMurosRomana);
        tvResultadoMurosYSoleraRomana = view.findViewById(R.id.tvResultadoMurosYSoleraRomana);
        tvResultadoEscaleraTotal = view.findViewById(R.id.tvResultadoEscaleraTotal);
        tvResultadoTotalPiscina = view.findViewById(R.id.tvResultadoTotalPiscina);
        tvResultadoPerimetroPiscinaRomana = view.findViewById(R.id.tvResultadoPerimetroPiscinaRomana);
        tvResultadoMediacanasRomana = view.findViewById(R.id.tvResultadoMediacanasRomana);
        tvResultadoDiagonalRomana = view.findViewById(R.id.tvResultadoDiagonalRomana);

        // Configurar TextWatchers para calcular automáticamente los resultados
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                calcularResultados();
            }
        };

        // Asignar el TextWatcher a los campos relevantes
        etValorMRomana.addTextChangedListener(textWatcher);
        etValorCapitalMRomana.addTextChangedListener(textWatcher);
        etValorHRomana.addTextChangedListener(textWatcher);
        etValorCapitalHRomana.addTextChangedListener(textWatcher);
        etValorEscalera.addTextChangedListener(textWatcher);

        // Inicializar el ViewModel
        rectangularRomanaViewModel = new ViewModelProvider(requireActivity()).get(RectangularRomanaViewModel.class);

        // Configurar el botón de guardar
        Button btnGuardar = view.findViewById(R.id.btnGuardarRomana);
        btnGuardar.setOnClickListener(this::guardarProyecto);
    }

    // Método para calcular los resultados
    private void calcularResultados() {
        double m = obtenerValorDeCampo(etValorMRomana);
        double M = obtenerValorDeCampo(etValorCapitalMRomana);
        double h = obtenerValorDeCampo(etValorHRomana);
        double H = obtenerValorDeCampo(etValorCapitalHRomana);
        double escalera = obtenerValorDeCampo(etValorEscalera);

        // Realizar cálculos
        double solera = m * M;
        double muros = (M * (h + H)) + (H * m) + ((m - 2 * escalera) * h);
        double murosYSolera = muros + solera;
        double soleraEscalera = Math.PI * escalera * escalera * 0.5;
        double murosEscalera = Math.PI * escalera * h;
        double perimetroEscalera = Math.PI * escalera;
        double escaleraTotal = soleraEscalera + murosEscalera;
        double totalPiscina = murosYSolera + escaleraTotal;
        double perimetro = 2 * m + 2 * M - 2 * escalera + perimetroEscalera;
        double mediacanas = m + 2 * M + 2 * h + 2 * H + perimetroEscalera + (m - 2 * escalera);
        double diagonal = Math.sqrt((m * m) + (M * M));

        // Mostrar resultados en los TextViews
        tvResultadoSoleraRomana.setText(String.format("Solera: %.2f m²", solera));
        tvResultadoMurosRomana.setText(String.format("Muros: %.2f m²", muros));
        tvResultadoMurosYSoleraRomana.setText(String.format("Muros y Solera: %.2f m²", murosYSolera));
        tvResultadoEscaleraTotal.setText(String.format("Escalera Total: %.2f m²", escaleraTotal));
        tvResultadoTotalPiscina.setText(String.format("Total Piscina: %.2f m²", totalPiscina));
        tvResultadoPerimetroPiscinaRomana.setText(String.format("Perímetro: %.2f ml", perimetro));
        tvResultadoMediacanasRomana.setText(String.format("Mediacañas: %.2f ml", mediacanas));
        tvResultadoDiagonalRomana.setText(String.format("Diagonal: %.2f ml", diagonal));
    }

    // Método para obtener el valor numérico de un campo
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

    // Método para guardar el proyecto
    private void guardarProyecto(View view) {
        String nombreProyecto = etNombreProyectoRomana.getText().toString().trim();
        if (nombreProyecto.isEmpty()) {
            Toast.makeText(requireContext(), "El nombre del proyecto no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        // Preparar los detalles
        String detallesRectangular = String.format(
                " %s\n %s\n %s\n %s\n %s\n %s\n %s\n %s\n",
                tvResultadoSoleraRomana.getText().toString(),
                tvResultadoMurosRomana.getText().toString(),
                tvResultadoMurosYSoleraRomana.getText().toString(),
                tvResultadoEscaleraTotal.getText().toString(),
                tvResultadoTotalPiscina.getText().toString(),
                tvResultadoPerimetroPiscinaRomana.getText().toString(),
                tvResultadoMediacanasRomana.getText().toString(),
                tvResultadoDiagonalRomana.getText().toString()
        );

        // Crear proyecto y agregarlo al ViewModel
        RectangularRomanaProject proyecto = new RectangularRomanaProject(nombreProyecto, detallesRectangular);
        rectangularRomanaViewModel.addProject(proyecto);

        Toast.makeText(requireContext(), "Proyecto guardado exitosamente", Toast.LENGTH_SHORT).show();

        // Limpiar campos
        etNombreProyectoRomana.setText("");
        etValorMRomana.setText("");
        etValorCapitalMRomana.setText("");
        etValorHRomana.setText("");
        etValorCapitalHRomana.setText("");
        etValorEscalera.setText("");

        // Navegar de regreso
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_rectangular_Romana_to_bottom1Fragment);
    }
}
