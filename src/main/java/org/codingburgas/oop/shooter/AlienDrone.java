package org.codingburgas.oop.shooter;

import org.codingburgas.oop.shooter.animation.IAnimator;
import org.codingburgas.oop.shooter.animation.SimpleVerticalAnimator;

import java.awt.*;

/**
 * class AlienDrone
 * <p>
 * Simple alien drone
 *
 * @author Martin Dobrev
 */
public class AlienDrone extends Alien {

  private static final String DRONE_IMAGE_URL = "Drakir_Race/Spaceships/drone-1.png";

  private final IAnimator animator;

  public AlienDrone(int x, int y) {
    super(x, y, DRONE_IMAGE_URL);
    animator = new SimpleVerticalAnimator(4);
  }

  @Override
  public void move() {

    final Point nextPosition = animator.getNextPosition(new Point(getX(), getY()));
    this.x = nextPosition.x;
    this.y = nextPosition.y;
  }
}
