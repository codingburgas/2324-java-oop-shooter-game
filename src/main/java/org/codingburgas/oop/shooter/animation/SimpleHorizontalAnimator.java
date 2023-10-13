package org.codingburgas.oop.shooter.animation;

import org.codingburgas.oop.shooter.GameBoard;

import java.awt.*;

public class SimpleHorizontalAnimator implements IAnimator {

  private final int velocity;
  private int direction = -1;

  public SimpleHorizontalAnimator(int velocity) {
    this.velocity = velocity;
  }

  @Override
  public Point getNextPosition(Point previousPosition) {
    Point nextPosition = new Point(previousPosition);
    if (previousPosition.x < 0) {
      nextPosition.x = 0;
      direction = 1;
    }
    if (previousPosition.x > GameBoard.BOARD_WIDTH) {
      nextPosition.x = GameBoard.BOARD_WIDTH;
      direction = -1;
    }
    nextPosition.x += direction * velocity;
    return nextPosition;
  }
}
