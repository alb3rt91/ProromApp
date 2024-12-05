package com.example.prorom;

public class RectangularRomanaProject extends BaseProject {
    private final String name;
    private final String details;

    public RectangularRomanaProject(String name, String details) {
        this.name = name;
        this.details = details;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDetails() {
        return details;
    }
}