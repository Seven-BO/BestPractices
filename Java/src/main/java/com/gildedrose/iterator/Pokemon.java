package com.gildedrose.iterator;

public class Pokemon {
    private String name;
    private String type;
    private int power;

    public Pokemon(String name, String type, int power) {
        this.name = name;
        this.type = type;
        this.power = power;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public int getPower() { return power; }

    @Override
    public String toString() {
        return name + " (" + type + ", Power: " + power + ")";
    }
}