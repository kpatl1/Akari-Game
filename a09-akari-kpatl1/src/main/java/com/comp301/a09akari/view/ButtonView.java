package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonView implements FXComponent {

  private final ClassicMvcController controller;

  public ButtonView(ClassicMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox buttonPanel = new HBox();
    Button nextButton = new Button("Next");
    Button prevButton = new Button("Last");
    Button resetButton = new Button("Reset");
    Button randButton = new Button("Random");
    Button colorBlindButton = new Button("Deuteranope and Protanomaly Mode");

    nextButton.setOnAction(e -> controller.clickNextPuzzle());
    prevButton.setOnAction(e -> controller.clickPrevPuzzle());
    resetButton.setOnAction(e -> controller.clickResetPuzzle());
    randButton.setOnAction(e -> controller.clickRandPuzzle());
    colorBlindButton.setOnAction(e -> controller.clickColorBlind());
    buttonPanel
        .getChildren()
        .addAll(nextButton, prevButton, resetButton, randButton, colorBlindButton);

    return buttonPanel;
  }
}
