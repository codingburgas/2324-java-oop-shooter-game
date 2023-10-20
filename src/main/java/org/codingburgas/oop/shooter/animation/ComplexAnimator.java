package org.codingburgas.oop.shooter.animation;

import java.awt.*;

public class ComplexAnimator implements IAnimator {

  private final SimpleVerticalAnimator simpleVerticalAnimator = new SimpleVerticalAnimator(3);
  private final SimpleHorizontalAnimator simpleHorizontalAnimator = new SimpleHorizontalAnimator(2);

  @Override
  public Point getNextPosition(Point previousPosition) {
    if (previousPosition.y > 350) {
      return simpleHorizontalAnimator.getNextPosition(previousPosition);
    } else {
      return simpleVerticalAnimator.getNextPosition(previousPosition);
    }
  }
}