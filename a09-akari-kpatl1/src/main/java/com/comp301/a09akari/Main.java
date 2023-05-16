package com.comp301.a09akari;

import com.comp301.a09akari.view.AppLauncher;
import javafx.application.Application;

public class Main {
  public static void main(String[] args) {
    Application.launch(AppLauncher.class);
  }
}

/*
import com.comp301.a09akari.model.ModelImpl;
import com.comp301.a09akari.model.PuzzleImpl;
import com.comp301.a09akari.model.PuzzleLibraryImpl;

public class Main {
  public static void main(String[] args) {
    int[][] puzzleA = {
      {6, 6, 6, 6, 6, 0, 6},
      {2, 6, 6, 6, 5, 6, 6},
      {6, 3, 6, 6, 6, 6, 6},
      {6, 6, 6, 6, 6, 6, 6},
      {6, 6, 6, 6, 6, 4, 6},
      {6, 6, 5, 6, 6, 6, 5},
      {6, 2, 6, 6, 6, 6, 6}
    };

    int[][] puzzleB = {
      {6, 6},
      {6, 6}
    };

    int[][] puzzleC = {
      {6, 6, 6, 6, 6},
      {6, 4, 6, 6, 6},
      {6, 6, 6, 6, 6}
    };

    int[][] puzzleD = {
      {6, 6, 6, 6, 6},
      {6, 4, 6, 6, 6},
      {6, 6, 6, 6, 6},
      {6, 1, 6, 2, 6},
      {6, 6, 6, 6, 6}
    };

    int[][] puzzleE = {{6}};

    PuzzleLibraryImpl library = new PuzzleLibraryImpl();
    library.addPuzzle(new PuzzleImpl(puzzleA));
    library.addPuzzle(new PuzzleImpl(puzzleB));
    library.addPuzzle(new PuzzleImpl(puzzleC));
    library.addPuzzle(new PuzzleImpl(puzzleD));
    library.addPuzzle(new PuzzleImpl(puzzleE));

    ModelImpl model = new ModelImpl(library);

    testPuzzle(model, 0);
    testPuzzle(model, 1);
    testPuzzle(model, 2);
    testPuzzle(model, 3);
  }

  public static void testPuzzle(ModelImpl model, int puzzleIndex) {
    model.setActivePuzzleIndex(puzzleIndex);
    //K.P: this puzzle should return false
    if (puzzleIndex == 0) {
      model.addLamp(0, 2);
      model.addLamp(1, 1);
      model.addLamp(2, 0);
      model.addLamp(2, 6);
      model.addLamp(3, 1);
      model.addLamp(3, 5);
      model.addLamp(4, 4);
      model.addLamp(4, 6);
      model.addLamp(5, 5);
      model.addLamp(5, 1);
      model.addLamp(6, 2);
    }
    if (puzzleIndex == 1) {
      model.addLamp(0, 0);
      model.addLamp(1, 1);
    }
    if (puzzleIndex == 2) {
      model.addLamp(0, 1);
      model.addLamp(1, 0);
      model.addLamp(2, 1);
      model.addLamp(1, 2);
    }
    if (puzzleIndex == 3) {
      model.addLamp(0, 1);
      model.addLamp(1, 0);
      model.addLamp(2, 1);
      model.addLamp(1, 2);
      model.addLamp(3, 4);
      model.addLamp(4, 3);
    }
    if (puzzleIndex == 4) {
      model.addLamp(0, 0);
    }

    System.out.println("Is puzzle " + (puzzleIndex + 1) + " solved? " + model.isSolved());
  }
}
*/
