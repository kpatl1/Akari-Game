package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class PuzzleView implements FXComponent {
  private final ClassicMvcController controller;

  private final Model model;
  private final GridPane gridPane;

  public PuzzleView(ClassicMvcController controller) {
    this.model = controller.getModel();
    this.controller = controller;
    this.gridPane = new GridPane();
  }

  public Parent render() {
    gridPane.getChildren().clear();
    int paneWidth = 85;
    int paneHeight = 85;
    int width = model.getActivePuzzle().getWidth();
    int height = model.getActivePuzzle().getHeight();

    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(85, 85); // Set the preferred size for StackPane
        CellType cellType = model.getActivePuzzle().getCellType(r, c);
        switch (cellType) {
          case CLUE:
            stackPane.setBackground(
                new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            Label clueInt =
                new Label(
                    ((Integer) controller.getModel().getActivePuzzle().getClue(r, c)).toString());
            if (controller.getModel().isClueSatisfied(r, c)) {
              clueInt.setTextFill(Color.GREEN);
              clueInt.setScaleX(2.2);
              clueInt.scaleXProperty();
              clueInt.setScaleY(2.2);
              clueInt.scaleYProperty();
            } else {
              clueInt.setScaleX(2.2);
              clueInt.scaleXProperty();
              clueInt.setScaleY(2.2);
              clueInt.scaleYProperty();
              clueInt.setTextFill(Color.WHITE);
            }
            stackPane.getChildren().add(clueInt);
            break;
          case WALL:
            stackPane.setBackground(
                new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
            break;
          case CORRIDOR:
            if (controller.getModel().isLit(r, c)) {
              if (!controller.getModel().getisColorBlind()) {
                stackPane.setBackground(
                    new Background(
                        new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
              } else {
                stackPane.setBackground(
                    new Background(new BackgroundFill(Color.TAN, CornerRadii.EMPTY, Insets.EMPTY)));
              }
              if (controller.getModel().isLamp(r, c)) {
                if (controller.getModel().isLampIllegal(r, c)) {
                  if (!controller.getModel().getisColorBlind()) {
                    stackPane.setBackground(
                        new Background(
                            new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                  } else if (controller.getModel().getisColorBlind()) {
                    stackPane.setBackground(
                        new Background(
                            new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                  }
                }
                ImageView image = new ImageView(new Image("light-bulb.png"));
                image.setFitHeight(0.5 * paneHeight);
                image.setFitWidth(0.5 * paneWidth);
                stackPane.getChildren().add(image);
              }
            } else {
              stackPane.setBackground(
                  new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            }
            int rfin = r;
            int cfin = c;
            stackPane.setOnMouseClicked(e -> controller.clickCell(rfin, cfin));
            break;
        }
        gridPane.add(stackPane, c, r);
      }
    }

    gridPane.setGridLinesVisible(true);
    return gridPane;
  }
}
