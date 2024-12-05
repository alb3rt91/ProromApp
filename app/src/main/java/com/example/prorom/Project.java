package com.example.prorom;

/**
 * Clase que representa un proyecto estándar.
 * Extiende la clase base BaseProject.
 */
public class Project extends BaseProject {

    private final String name;    // Nombre del proyecto.
    private final String details; // Detalles del proyecto.

    /**
     * Constructor para inicializar el proyecto con nombre y detalles.
     *
     * @param name Nombre del proyecto.
     * @param details Detalles del proyecto.
     */
    public Project(String name, String details) {
        this.name = name;
        this.details = details;
    }

    /**
     * Devuelve el nombre del proyecto.
     *
     * @return Nombre del proyecto.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Devuelve los detalles del proyecto.
     *
     * @return Detalles del proyecto.
     */
    @Override
    public String getDetails() {
        return details;
    }
}
