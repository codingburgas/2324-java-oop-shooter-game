package org.codingburgas.oop.shooter.animation;

import java.awt.*;

/**
 * Main interface for object animation
 * <p>
 *
 * Has only one method - getNextPosition. The method expects the object's position as input
 * and returns the next position.
 *
 * @author Martin Dobrev
 */
public interface IAnimator {
  Point getNextPosition(Point previousPosition);
}
