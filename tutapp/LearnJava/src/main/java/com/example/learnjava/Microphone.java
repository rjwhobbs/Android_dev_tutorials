package com.example.learnjava;

public class Microphone {
    private String name;
    private String color;
    private int model;

    //Overloading constructors
    public Microphone(){}
    public Microphone(String name, String color, int model) {
        this.name = name;
        this.color = color;
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getModel() {
        return model;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setModel(int model) {
        this.model = model;
    }
}
