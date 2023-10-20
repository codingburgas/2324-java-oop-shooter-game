package org.codingburgas.oop.shooter.animation;

import org.codingburgas.oop.shooter.GameBoard;

import java.awt.*;

public class ComplexAnimatorBadExample extends SimpleVerticalAnimator {

  int direction = -1;
  public ComplexAnimatorBadExample(int velocity) {
    super(velocity);
  }

  @Override
  public Point getNextPosition(Point previousPosition) {
    if (previousPosition.y > 350) {
      Point nextPosition = new Point(previousPosition);
      if (previousPosition.x < 0) {
        nextPosition.x = 0;
        direction = 1;
      }
      if (previousPosition.x > GameBoard.BOARD_WIDTH) {
        nextPosition.x = GameBoard.BOARD_WIDTH;
        direction = -1;
      }
      nextPosition.x += direction * getVelocity();
      return nextPosition;
    }
    return super.getNextPosition(previousPosition);
  }
}