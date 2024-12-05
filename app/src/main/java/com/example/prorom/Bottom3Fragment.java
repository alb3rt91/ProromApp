package com.example.prorom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragmento simple para Bottom3.
 */
public class Bottom3Fragment extends Fragment {

    // Constantes para los argumentos del fragmento.
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Variables para los parámetros.
    private String mParam1;
    private String mParam2;

    public Bottom3Fragment() {
        // Constructor público requerido.
    }

    /**
     * Método de fábrica para crear una nueva instancia del fragmento.
     *
     * @param param1 Parámetro 1.
     * @param param2 Parámetro 2.
     * @return Nueva instancia de Bottom3Fragment.
     */
    public static Bottom3Fragment newInstance(String param1, String param2) {
        Bottom3Fragment fragment = new Bottom3Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Obtiene los parámetros desde los argumentos.
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla el diseño del fragmento.
        return inflater.inflate(R.layout.fragment_bottom3, container, false);
    }
}
