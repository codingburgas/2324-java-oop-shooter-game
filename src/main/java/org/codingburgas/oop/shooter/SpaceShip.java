package org.codingburgas.oop.shooter;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * class SpaceShip
 * <p/>
 * Movement is done by KeyEvents. The events are registered on the board and passed to the Spaceship object.
 * The ship has also a predefined speed (test different values for better user experience).
 *
 * @author Martin Dobrev
 */
public class SpaceShip extends Sprite {

  private final static String SPACESHIP_IMAGE_URL = "player_ship1.png";
  private int dx;
  private int dy;

  private final int speed;
  private final List<Missile> missiles = new ArrayList<>();

  private int health;

  public SpaceShip(int x, int y, int speed) {
    super(x, y, SPACESHIP_IMAGE_URL);
    this.speed = speed;
    this.health = 100;
  }

  public void move() {

    x += dx;
    y += dy;

    if (x < 1) {
      x = 1;
    }

    if (y < 1) {
      y = 1;
    }
  }

  public List<Missile> getMissiles() {
    return missiles;
  }

  /**
   * Processes the key pressed keyboard events
   * <br/>
   * When the user holds the arrow keys, the ship is moved accordingly.
   * <br/>
   * The movement is actually done in the move method. This method only sets the dx, dy to the speed values
   * so that the next move can update the position.
   * <br/>
   * If SPACE is pressed, the ship fires missiles.
   *
   * @param keyEvent press KeyEvent
   */
  public void keyPressed(KeyEvent keyEvent) {
    int key = keyEvent.getKeyCode();
    switch (key) {
      case KeyEvent.VK_SPACE -> fire();
      case KeyEvent.VK_LEFT -> dx = -speed;
      case KeyEvent.VK_RIGHT -> dx = speed;
      case KeyEvent.VK_UP -> dy = -speed;
      case KeyEvent.VK_DOWN -> dy = speed;
    }
  }

  /**
   * Creates two missiles and fires.
   * <br/>
   * Their position is set to the left and right weapons of the image.
   */
  public void fire() {
    missiles.add(new Missile(x + 10, y + (height / 2) + 10));
    missiles.add(new Missile(x + width - 10, y + (height / 2) + 10));
  }

  /**
   * Resets the key events (on key release)
   * <br/>
   * Sets the
   *
   * @param keyEvent release KeyEvent
   */
  public void keyReleased(KeyEvent keyEvent) {
    int key = keyEvent.getKeyCode();
    switch (key) {
      case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> dx = 0;
      case KeyEvent.VK_UP, KeyEvent.VK_DOWN -> dy = 0;
    }
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public boolean takeDamage(int damage) {
    health = health - damage;
    return health > 0;
  }
}