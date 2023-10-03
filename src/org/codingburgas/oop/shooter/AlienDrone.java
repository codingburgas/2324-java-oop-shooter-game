package org.codingburgas.oop.shooter;

/**
 * class AlienDrone
 * <p>
 * Simple alien drone
 *
 * @author Martin Dobrev
 */
public class AlienDrone extends Alien {

  private static final String DRONE_IMAGE_URL = "src/resources/Drakir_Race/Spaceships/drone-1.png";

  public AlienDrone(int x, int y) {
    super(x, y, DRONE_IMAGE_URL);
  }
}
