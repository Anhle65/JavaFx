package models;

import java.util.Random;

public class Rocket {
    private static final String[] FUEL_LEVELS = {"Full", "Half-full", "Almost empty", "Empty"};
    private static final String[] CLEANLINESS_LEVELS = {"Sparkling", "Clean", "Ok", "Dirty"};
    private String name;
    private String fuel;
    private String cleanliness;

    public Rocket(String name, String fuel, String cleanliness) {
        this.name = name;
        this.fuel = fuel;
        this.cleanliness = cleanliness;
    }

    public Rocket(String name) {
        Random random = new Random();
        this.name = name;
        this.fuel = FUEL_LEVELS[random.nextInt(FUEL_LEVELS.length)];
        this.cleanliness = CLEANLINESS_LEVELS[random.nextInt(CLEANLINESS_LEVELS.length)];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(String cleanliness) {
        this.cleanliness = cleanliness;
    }
}