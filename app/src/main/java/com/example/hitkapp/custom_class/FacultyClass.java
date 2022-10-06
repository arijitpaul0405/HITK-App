package com.example.hitkapp.custom_class;

public class FacultyClass {
    private final String name;
    private final String designation;
    private final String image;

    public FacultyClass(String name, String designation, String image) {
        this.name = name;
        this.designation = designation;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getImage() {
        return image;
    }
}
