package com.example.prorom;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.prorom.databinding.ActivityMainBinding;

/**
 * Actividad principal de la aplicación.
 */
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding; // Binding para acceder a las vistas.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflar el diseño y configurar el binding.
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());

        // Configurar la Toolbar como la barra de acciones.
        setSupportActionBar(binding.toolbar);

        // Asegurar que el título de la Toolbar sea el nombre de la aplicación.
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name);
        }

        // Configurar NavController para gestionar la navegación.
        NavController navController = ((NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment)).getNavController();

        // Vincular la barra de navegación inferior y la Toolbar con NavController.
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
        NavigationUI.setupWithNavController(binding.toolbar, navController);
    }
}
