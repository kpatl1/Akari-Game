package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class MainView implements FXComponent {

  private final ClassicMvcController controller;

  public MainView(ClassicMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();
    MessageView msgView = new MessageView(controller);
    PuzzleView pzlView = new PuzzleView(controller);
    ButtonView buttonView = new ButtonView(controller);
    layout.getChildren().add(msgView.render());
    layout.getChildren().add(buttonView.render());
    layout.getChildren().add(pzlView.render());
    return layout;
  }
}
