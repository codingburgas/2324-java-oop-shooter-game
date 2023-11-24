package org.codingburgas.oop.shooter;

/**
 * class AlienFighter
 * <p/>
 * Simple fighter alien.
 *
 * @author Martin Dobrev
 */
public class AlienFighter extends Alien {

  private static final String FIGHTER_IMAGE_URL = "Drakir_Race/Spaceships/fighter-a-1.png";

  public AlienFighter(int x, int y) {
    super(x, y, FIGHTER_IMAGE_URL);
    this.setHealth(30);
  }
}
