package com.example.prorom;

/**
 * Clase que representa un proyecto de piscina rectangular con escalera rectangular.
 * Extiende la clase base BaseProject.
 */
public class RectangularRectangularProject extends BaseProject {

    private final String name;    // Nombre del proyecto.
    private final String details; // Detalles del proyecto.

    /**
     * Constructor para inicializar el proyecto con un nombre y detalles.
     *
     * @param name Nombre del proyecto.
     * @param details Detalles del proyecto.
     */
    public RectangularRectangularProject(String name, String details) {
        this.name = name;
        this.details = details;
    }

    /**
     * Obtiene el nombre del proyecto.
     *
     * @return Nombre del proyecto.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Obtiene los detalles del proyecto.
     *
     * @return Detalles del proyecto.
     */
    @Override
    public String getDetails() {
        return details;
    }
}
