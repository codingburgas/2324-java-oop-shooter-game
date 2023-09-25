package org.codingburgas.oop.shooter;

public class Alien extends Sprite {

  public Alien(int x, int y) {
    super(x, y);

    initAlien();
  }

  private void initAlien() {

    loadImage("src/resources/Drakir_Race/Spaceships/drone-1.png");
    getImageDimensions();
  }

  public void move() {

    if (y > Board.B_HEIGHT) {
      y = -10;
    }

    y += 1;
  }
}
