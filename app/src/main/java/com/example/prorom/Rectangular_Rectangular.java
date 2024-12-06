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
 * Fragmento para gestionar proyectos rectangulares con escaleras rectangulares.
 */
public class Rectangular_Rectangular extends Fragment {

    // Campos de entrada.
    private EditText etNombreProyectoRectangular, etValorMRectangular, etValorCapitalMRectangular, etValorHRectangular, etValorCapitalHRectangular,
            etValorEscalera1Rectangular, etValorEscalera2Rectangular;

    // Resultados.
    private TextView tvResultadoSoleraRectangular, tvResultadoMurosRectangular, tvResultadoMurosYSoleraRectangular,
            tvResultadoTotalEscaleraRectangular, tvResultadoTotalPiscinaRectangular, tvResultadoPerimetroPiscinaRectangular, tvResultadoMediacanasRectangular, tvResultadoDiagonalRectangular;

    private RectangularRectangularViewModel rectangularRectangularViewModelViewModel;

    public Rectangular_Rectangular() {
        super(R.layout.fragment_rectangular__rectangular); // Asocia el diseño al fragmento.
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializar los campos de entrada.
        etNombreProyectoRectangular = view.findViewById(R.id.etNombreProyectoRectangular);
        etValorMRectangular = view.findViewById(R.id.etValorMRectangular);
        etValorCapitalMRectangular = view.findViewById(R.id.etValorCapitalMRectangular);
        etValorHRectangular = view.findViewById(R.id.etValorHRectangular);
        etValorCapitalHRectangular = view.findViewById(R.id.etValorCapitalHRectangular);
        etValorEscalera1Rectangular = view.findViewById(R.id.etValorEscalera1Rectangular);
        etValorEscalera2Rectangular = view.findViewById(R.id.etValorEscalera2Rectangular);

        // Inicializar los TextViews para los resultados.
        tvResultadoSoleraRectangular = view.findViewById(R.id.tvResultadoSoleraRectangular);
        tvResultadoMurosRectangular = view.findViewById(R.id.tvResultadoMurosRectangular);
        tvResultadoMurosYSoleraRectangular = view.findViewById(R.id.tvResultadoMurosYSoleraRectangular);
        tvResultadoTotalEscaleraRectangular = view.findViewById(R.id.tvResultadoEscaleraTotalRectangular);
        tvResultadoTotalPiscinaRectangular = view.findViewById(R.id.tvResultadoTotalPiscinaRectangular);
        tvResultadoPerimetroPiscinaRectangular = view.findViewById(R.id.tvResultadoPerimetroPiscinaRectangular);
        tvResultadoMediacanasRectangular = view.findViewById(R.id.tvResultadoMediacanasRectangular);
        tvResultadoDiagonalRectangular = view.findViewById(R.id.tvResultadoDiagonalRectangular);

        // Configurar TextWatchers para calcular automáticamente los resultados.
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

        // Añadir TextWatchers a los campos de entrada.
        etValorMRectangular.addTextChangedListener(textWatcher);
        etValorCapitalMRectangular.addTextChangedListener(textWatcher);
        etValorHRectangular.addTextChangedListener(textWatcher);
        etValorCapitalHRectangular.addTextChangedListener(textWatcher);
        etValorEscalera1Rectangular.addTextChangedListener(textWatcher);
        etValorEscalera2Rectangular.addTextChangedListener(textWatcher);

        // Inicializar el ViewModel.
        rectangularRectangularViewModelViewModel = new ViewModelProvider(requireActivity()).get(RectangularRectangularViewModel.class);

        // Configurar el botón de guardar.
        Button btnGuardar = view.findViewById(R.id.btnGuardarRectangular);
        btnGuardar.setOnClickListener(this::guardarProyecto);
    }

    // Calcula los resultados con base en los valores ingresados.
    private void calcularResultados() {
        double m = obtenerValorDeCampo(etValorMRectangular);
        double M = obtenerValorDeCampo(etValorCapitalMRectangular);
        double h = obtenerValorDeCampo(etValorHRectangular);
        double H = obtenerValorDeCampo(etValorCapitalHRectangular);
        double l = obtenerValorDeCampo(etValorEscalera1Rectangular);
        double L = obtenerValorDeCampo(etValorEscalera2Rectangular);

        // Realizar cálculos.
        double solera = m * M;
        double muros = M * (h + H) + H * m + (m - L) * h;
        double soleraYMuros = solera + muros;
        double soleraEscalera = l * L;
        double murosEscalera = (l + l + L) * h;
        double totalEscalera = soleraEscalera + murosEscalera;
        double totalPiscina = soleraYMuros + totalEscalera;
        double perimetro = 2 * m + 2 * M + 2 * l;
        double mediacanas = 2 * (M + m) + 2 * (h + H);
        double diagonal = Math.sqrt((m * m) + (M * M));

        // Actualizar los TextViews con los resultados.
        tvResultadoSoleraRectangular.setText(String.format("Solera: %.2f m²", solera));
        tvResultadoMurosRectangular.setText(String.format("Muros: %.2f m²", muros));
        tvResultadoMurosYSoleraRectangular.setText(String.format("Solera y Muros: %.2f m²", soleraYMuros));
        tvResultadoTotalEscaleraRectangular.setText(String.format("Total Escalera: %.2f m²", totalEscalera));
        tvResultadoTotalPiscinaRectangular.setText(String.format("Total Piscina: %.2f m²", totalPiscina));
        tvResultadoPerimetroPiscinaRectangular.setText(String.format("Perímetro: %.2f ml", perimetro));
        tvResultadoMediacanasRectangular.setText(String.format("Mediacañas: %.2f ml", mediacanas));
        tvResultadoDiagonalRectangular.setText(String.format("Diagonal: %.2f ml", diagonal));
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
            return 0.0; // Si el texto no es válido, devuelve 0.
        }
    }

    // Guarda el proyecto y navega de regreso.
    private void guardarProyecto(View view) {
        String nombreProyecto = etNombreProyectoRectangular.getText().toString().trim();
        if (nombreProyecto.isEmpty()) {
            Toast.makeText(requireContext(), "El nombre del proyecto no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear detalles del proyecto.
        String detallesRectangular = String.format(
                " %s\n %s\n  %s\n %s\n %s\n %s\n %s\n %s\n",
                tvResultadoSoleraRectangular.getText().toString(),
                tvResultadoMurosRectangular.getText().toString(),
                tvResultadoMurosYSoleraRectangular.getText().toString(),
                tvResultadoTotalEscaleraRectangular.getText().toString(),
                tvResultadoTotalPiscinaRectangular.getText().toString(),
                tvResultadoPerimetroPiscinaRectangular.getText().toString(),
                tvResultadoMediacanasRectangular.getText().toString(),
                tvResultadoDiagonalRectangular.getText().toString()
        );

        // Agregar proyecto al ViewModel.
        RectangularRectangularProject proyecto = new RectangularRectangularProject(nombreProyecto, detallesRectangular);
        rectangularRectangularViewModelViewModel.addProject(proyecto);

        Toast.makeText(requireContext(), "Proyecto guardado exitosamente", Toast.LENGTH_SHORT).show();

        // Limpiar los campos de entrada.
        etNombreProyectoRectangular.setText("");
        etValorMRectangular.setText("");
        etValorCapitalMRectangular.setText("");
        etValorHRectangular.setText("");
        etValorCapitalHRectangular.setText("");
        etValorEscalera1Rectangular.setText("");
        etValorEscalera2Rectangular.setText("");

        // Navegar de regreso al fragmento anterior.
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_rectangular_Rectangular_to_bottom1Fragment);
    }
}
