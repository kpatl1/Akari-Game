package com.comp301.a09akari.view;

import com.comp301.a09akari.Main;
import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    PuzzleLibrary pzlLib = new PuzzleLibraryImpl();
    pzlLib.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_01));
    pzlLib.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_02));
    pzlLib.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_03));
    pzlLib.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_04));
    pzlLib.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_05));

    Model model = new ModelImpl(pzlLib);
    ClassicMvcController controller = new ControllerImpl(model);
    MainView mainV = new MainView(controller);
    Scene scene = new Scene(mainV.render());
    primaryStage.setScene(scene);
    model.addObserver(
        (Model m) -> {
          scene.setRoot(mainV.render());
        });

    primaryStage.setTitle("Akari");
    primaryStage.show();
  }
}
