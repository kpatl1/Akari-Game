package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelImpl;

public class ControllerImpl implements ClassicMvcController {

  private final Model model;

  public ControllerImpl(Model model) {
    if (model == null) throw new IllegalArgumentException("Model cannot be null");
    this.model = model;
  }

  @Override
  public void clickNextPuzzle() {
    if (this.model.getActivePuzzleIndex() == this.model.getPuzzleLibrarySize() - 1) {
      return;
    }
    this.model.setActivePuzzleIndex(model.getActivePuzzleIndex() + 1);
  }

  @Override
  public void clickPrevPuzzle() {
    if (this.model.getActivePuzzleIndex() == 0) {
      return;
    }
    this.model.setActivePuzzleIndex(this.model.getActivePuzzleIndex() - 1);
  }

  @Override
  public void clickRandPuzzle() {
    int currIdx = model.getActivePuzzleIndex();
    int randIdx = (int) (Math.random() * this.model.getPuzzleLibrarySize());
    if (randIdx == currIdx) {
      this.clickRandPuzzle();
      return;
    }
    this.model.setActivePuzzleIndex(randIdx);
  }

  public void clickColorBlind() {
    model.setColorBlind(!model.getisColorBlind());
  }

  @Override
  public void clickResetPuzzle() {
    this.model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (this.model.getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
      if (!this.model.isLamp(r, c)) {
        this.model.addLamp(r, c);
      } else {
        this.model.removeLamp(r, c);
      }
    }
  }

  @Override
  public Model getModel() {
    return this.model;
  }
}
