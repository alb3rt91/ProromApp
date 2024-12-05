package com.example.prorom;

public class RectangularRectangularProject extends BaseProject {
    private final String name;
    private final String details;

    public RectangularRectangularProject(String name, String details) {
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
