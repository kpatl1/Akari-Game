package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {

  private final PuzzleLibrary pzlLib;
  private final List<ModelObserver> observers;
  private Puzzle activePuzzle;
  private int puzzleIndex;
  private boolean[][] lamps;
  private boolean checkingCurrent = false;

  private boolean isColorBlind = false;

  public ModelImpl(PuzzleLibrary pzlLib) {
    this.puzzleIndex = 0;
    this.pzlLib = pzlLib;
    this.activePuzzle = pzlLib.getPuzzle(this.puzzleIndex);
    this.observers = new ArrayList<ModelObserver>();
    this.lamps = new boolean[this.activePuzzle.getHeight()][this.activePuzzle.getWidth()];
  }

  @Override
  public boolean getisColorBlind() {
    return this.isColorBlind;
  }

  public void setColorBlind(boolean isColorBlind) {
    this.isColorBlind = isColorBlind;
    notifyObservers();
  }

  @Override
  public void addLamp(int r, int c) {
    if (r < 0 || r >= this.activePuzzle.getHeight() || c < 0 || c >= this.activePuzzle.getWidth()) {
      throw new IndexOutOfBoundsException("Out of bounds");
    }
    if (this.activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException("Not a corridor");
    }
    if (this.lamps[r][c]) {
      throw new IllegalArgumentException("Already a lamp");
    }
    this.lamps[r][c] = true;
    notifyObservers();
  }

  @Override
  public void removeLamp(int r, int c) {
    if (r < 0 || r >= this.activePuzzle.getHeight() || c < 0 || c >= this.activePuzzle.getWidth()) {
      throw new IndexOutOfBoundsException("Out of bounds");
    }
    if (this.activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException("Not a corridor");
    }
    if (!this.lamps[r][c]) {
      throw new IllegalArgumentException("Not a lamp");
    }
    this.lamps[r][c] = false;
    notifyObservers();
  }

  @Override
  public boolean isLit(int r, int c) {
    if (r < 0 || r >= this.activePuzzle.getHeight() || c < 0 || c >= this.activePuzzle.getWidth()) {
      throw new IndexOutOfBoundsException("Out of bounds");
    }
    if (this.activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException("Not a corridor");
    }
    if (this.lamps[r][c] && !checkingCurrent) {
      return true;
    }

    for (int i = c + 1; i < this.activePuzzle.getWidth(); i++) {
      if (this.lamps[r][i]) {
        return true;
      }
      if (this.activePuzzle.getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
    }
    for (int i = c - 1; i >= 0; i--) {
      if (this.lamps[r][i]) {
        return true;
      }
      if (this.activePuzzle.getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
    }

    for (int i = r + 1; i < this.activePuzzle.getHeight(); i++) {
      if (this.lamps[i][c]) {
        return true;
      }
      if (this.activePuzzle.getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
    }
    for (int i = r - 1; i >= 0; i--) {
      if (this.lamps[i][c]) {
        return true;
      }
      if (this.activePuzzle.getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (r < 0 || r >= this.activePuzzle.getHeight() || c < 0 || c >= this.activePuzzle.getWidth()) {
      throw new IndexOutOfBoundsException("Out of bounds");
    }
    if (this.activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException("Not a corridor");
    }
    return this.lamps[r][c];
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (r < 0 || r >= this.activePuzzle.getHeight() || c < 0 || c >= this.activePuzzle.getWidth()) {
      throw new IndexOutOfBoundsException("Out of bounds");
    }
    if (!this.lamps[r][c]) {
      throw new IllegalArgumentException("Cell does not contain a lamp");
    }
    this.checkingCurrent = true;
    boolean isIllegal = isLit(r, c);
    this.checkingCurrent = false;
    return isIllegal;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return this.activePuzzle;
  }

  @Override
  public int getActivePuzzleIndex() {
    return this.puzzleIndex;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index < 0 || index >= this.pzlLib.size()) {
      throw new IndexOutOfBoundsException("Out of bounds");
    }
    this.puzzleIndex = index;
    this.activePuzzle = this.pzlLib.getPuzzle(this.puzzleIndex);
    this.lamps = new boolean[this.activePuzzle.getHeight()][this.activePuzzle.getWidth()];
    notifyObservers();
  }

  @Override
  public int getPuzzleLibrarySize() {
    return this.pzlLib.size();
  }

  @Override
  public void resetPuzzle() {
    this.lamps = new boolean[this.activePuzzle.getHeight()][this.activePuzzle.getWidth()];
    notifyObservers();
  }

  @Override
  public boolean isSolved() {
    for (int i = 0; i < this.activePuzzle.getHeight(); i++) {
      for (int j = 0; j < this.activePuzzle.getWidth(); j++) {
        switch (this.activePuzzle.getCellType(i, j)) {
          case CORRIDOR:
            if (isLamp(i, j)) {
              if (isLampIllegal(i, j)) {
                return false;
              }
            }
            if (!isLit(i, j)) {
              return false;
            }
            break;
          case CLUE:
            if (!isClueSatisfied(i, j)) {
              return false;
            }
            break;
          case WALL:
            break;
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (r < 0 || r >= this.activePuzzle.getHeight() || c < 0 || c >= this.activePuzzle.getWidth()) {
      throw new IndexOutOfBoundsException("Out of bounds");
    }
    if (this.activePuzzle.getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException("Not a clue");
    }
    int count = 0;
    if (r > 0 && this.lamps[r - 1][c]) {
      count++;
    }
    if (r + 1 < this.activePuzzle.getHeight() && this.lamps[r + 1][c]) {
      count++;
    }
    if (c > 0 && this.lamps[r][c - 1]) {
      count++;
    }
    if (c + 1 < this.activePuzzle.getWidth() && this.lamps[r][c + 1]) {
      count++;
    }
    return count == this.activePuzzle.getClue(r, c);
  }

  @Override
  public void addObserver(ModelObserver observer) {
    this.observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    this.observers.remove(observer);
  }

  @Override
  public void notifyObservers() {
    for (ModelObserver observer : this.observers) {
      observer.update(this);
    }
  }
}
