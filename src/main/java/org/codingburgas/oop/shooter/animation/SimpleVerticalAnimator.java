package org.codingburgas.oop.shooter.animation;

import org.codingburgas.oop.shooter.GameBoard;

import java.awt.*;

/**
 * SimpleVerticalAnimator
 * <p>
 *
 * Updates only the vertical position of the object based on it's velocity
 *
 * @author Martin Dobrev
 */
public class SimpleVerticalAnimator implements IAnimator {

  /**
   * Pixels per frame to move the object
   */
  private int velocity;

  public SimpleVerticalAnimator(int velocity) {
    this.velocity = velocity;
  }

  @Override
  public Point getNextPosition(Point previousPosition) {
    Point nextPosition = new Point(previousPosition);

    // in case out of the board, place the object back at the top
    if (previousPosition.y > GameBoard.BOARD_HEIGHT) {
      nextPosition.y = -20;
    } else {
      nextPosition.y =+ velocity;
    }
    return nextPosition;
  }

  public int getVelocity() {
    return velocity;
  }

  public void setVelocity(int velocity) {
    this.velocity = velocity;
  }

}
