package org.codingburgas.oop.shooter;

public class Missile extends Sprite {

  private final int MISSILE_SPEED = 2;

  public Missile(int x, int y) {
    super(x, y);

    initMissile();
  }

  private void initMissile() {

    loadImage("src/resources/laser1.png");
    getImageDimensions();
  }

  public void move() {

    y -= MISSILE_SPEED;

    if (y < Board.HEIGHT) visible = false;
  }
}
