package com.davidvelz.diagnosticsystem.objects;

public class SolutionModel {
    private String description;
    private String key;
    private double price;
    private String solution_id;

    public SolutionModel() {
    }

    public SolutionModel(String description, String key, double price, String solution_id) {
        this.description = description;
        this.key = key;
        this.price = price;
        this.solution_id = solution_id;
    }

    public String getDescription() {
        return description;
    }

    public String getKey() {
        return key;
    }

    public double getPrice() {
        return price;
    }

    public String getSolution_id() {
        return solution_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSolution_id(String solution_id) {
        this.solution_id = solution_id;
    }
}
