package org.codingburgas.oop.shooter.animation;

import org.codingburgas.oop.shooter.GameBoard;

import java.awt.*;

public class AcceleratedVerticalAnimator implements IAnimator {

  @Override
  public Point getNextPosition(Point previousPosition) {
    int velocity = 1;
    if (previousPosition.y > GameBoard.BOARD_HEIGHT / 4) {
      velocity = 4;
    }
    var nextPosition = new Point(previousPosition);
    if (previousPosition.y > GameBoard.BOARD_HEIGHT) {
      nextPosition.y = -20;
    } else {
      nextPosition.y += velocity;
    }
    return nextPosition;
  }
}
