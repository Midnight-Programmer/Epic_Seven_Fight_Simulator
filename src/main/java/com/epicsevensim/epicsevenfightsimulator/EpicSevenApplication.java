package com.epicsevensim.epicsevenfightsimulator;

import com.epicsevensim.epicsevenfightsimulator.units.enemies.Boss;
import com.epicsevensim.epicsevenfightsimulator.units.Unit;
import com.epicsevensim.epicsevenfightsimulator.units.heroes.Sigret;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EpicSevenApplication extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader =
        new FXMLLoader(EpicSevenApplication.class.getResource("hello-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
    stage.setTitle("Epic Seven Fight Simulator");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    // launch();

    Boss target1 = new Boss(5000, 1941, 1000000, 243, 10.0, 50.0, 100, 80, 0, "Fire");
    ArrayList<Unit> targets = new ArrayList<>();
    targets.add(target1);

    Sigret hero1 = new Sigret(3500, 1100, 9000, 196, 35.0, 205.0, 65.0, 0.0, 5.0);

    ArrayList<Unit> myTeam = new ArrayList<>();
    myTeam.add(hero1);

    Simulation instance = new Simulation(myTeam, targets);

    instance.startBattle();

    System.out.println(target1.getHealth());
    System.out.println(myTeam.get(0).getHealth());
  }
}
