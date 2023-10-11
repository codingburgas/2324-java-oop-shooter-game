package org.codingburgas.oop.shooter;

/**
 * class Missile
 * <p/>
 * User missile implementation. Simple extension of sprite. Moves only vertically
 * <p/>
 * @author Martin Dobrev
 */
public class Missile extends Sprite {

  private final int MISSILE_SPEED = 2;

  public Missile(int x, int y) {
    super(x, y, "laser1.png");
  }

  public void move() {
    y -= MISSILE_SPEED;
    if (y < GameBoard.HEIGHT) visible = false;
  }
}
