package seng201.tut2;

import models.Rocket;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RocketManager {
    private String name;
    private List<Rocket> rocketList;
    private final List<Rocket> defaultRockets = new ArrayList<>();
    private final Consumer<RocketManager> setupScreenLauncher;
    private final Consumer<RocketManager> mainScreenLauncher;
    private final Runnable clearScreen;

    public RocketManager(Consumer<RocketManager> setupScreenLauncher, Consumer<RocketManager> mainScreenLauncher, Runnable clearScreen) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.mainScreenLauncher = mainScreenLauncher;
        this.clearScreen = clearScreen;
        defaultRockets.addAll(List.of(new Rocket("Space Shuttle"), new Rocket("Falcon 9"),
                new Rocket("Falcon Heavy"), new Rocket("Ariane 5"), new Rocket("Saturn 5"),
                new Rocket("Delta IV Heavy")));
        launchSetupScreen();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Rocket> getRocketList() {
        return rocketList;
    }

    public void setRocketList(List<Rocket> rocketList) {
        this.rocketList = rocketList;
    }

    public List<Rocket> getDefaultRockets() {
        return defaultRockets;
    }

    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }

    public void closeSetupScreen() {
        clearScreen.run();
        launchMainScreen();
    }

    public void launchMainScreen() {
        mainScreenLauncher.accept(this);
    }

    public void closeMainScreen() {
        System.exit(0);
    }
}
