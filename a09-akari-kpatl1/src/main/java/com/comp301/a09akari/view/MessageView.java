package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class MessageView implements FXComponent {

  ClassicMvcController controller;

  public MessageView(ClassicMvcController cont) {
    this.controller = cont;
  }

  @Override
  public Parent render() {
    Label winMsg = new Label("You Win!");
    int curIdx = controller.getModel().getActivePuzzleIndex() + 1;
    Label curGameIdx =
        new Label(
            "Current Game Index: " + curIdx + "/" + controller.getModel().getPuzzleLibrarySize());
    curGameIdx.setTextFill(Color.BLUE);
    curGameIdx.setScaleX(1.2);
    curGameIdx.scaleXProperty();
    curGameIdx.setScaleY(1.2);
    curGameIdx.scaleYProperty();
    winMsg.setTextFill(Color.GREEN);
    winMsg.setScaleX(2.2);
    winMsg.scaleXProperty();
    winMsg.setScaleY(2.2);
    winMsg.scaleYProperty();
    winMsg.centerShapeProperty();
    BorderPane bp = new BorderPane();
    BorderPane.setMargin(winMsg, new Insets(10, 0, 0, 152));
    BorderPane.setMargin(curGameIdx, new Insets(20, 50, 0, 0));
    bp.setRight(curGameIdx);
    if (controller.getModel().isSolved()) {
      bp.setCenter(winMsg);
    }
    return bp;
  }
}
