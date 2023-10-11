package org.codingburgas.oop.shooter;

/**
 * class Alien
 * <p/>
 * Abstract class to represent aliens. Extends Sprite and adds a move method
 * that is responsible for updating the alien's position on each frame.
 * <br/>
 * Consider overriding the move method in the classes that will extend Alien to change their behaviour.
 * <br/>
 * @author Martin Dobrev
 */
public abstract class Alien extends Sprite {

  public Alien(int x, int y, String imageUrl) {
    super(x, y, imageUrl);
  }

  public void move() {
    if (y > GameBoard.BOARD_HEIGHT) {
      y = -10;
    }
    y += 1;
  }
}
