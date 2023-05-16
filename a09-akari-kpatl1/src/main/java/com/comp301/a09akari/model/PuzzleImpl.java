package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {

  private final int[][] board;

  public PuzzleImpl(int[][] board) {
    this.board = board;
  }

  @Override
  public int getWidth() {

    return this.board[0].length;
  }

  @Override
  public int getHeight() {
    return this.board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r < 0 || r >= this.board.length || c < 0 || c >= this.board[0].length) {
      throw new IndexOutOfBoundsException();
    }
    int cell = this.board[r][c];
    if (cell >= 0 && cell <= 4) {
      return CellType.CLUE;
    } else if (cell == 5) {
      return CellType.WALL;
    } else if (cell == 6) {
      return CellType.CORRIDOR;
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public int getClue(int r, int c) {
    if (r < 0 || r >= this.board.length || c < 0 || c >= this.board[0].length) {
      throw new IndexOutOfBoundsException();
    }
    if (this.getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    return this.board[r][c];
  }
}
