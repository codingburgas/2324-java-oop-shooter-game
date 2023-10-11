package org.codingburgas.oop.shooter;

import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * Basic class to display an image on the board
 * <p>
 * The only properties needed are:
 * <p>
 *  - x position
 * <p>
 *  - y position
 * <p>
 *  - image to display (pass the image path as string)
 *
 * @author Martin Dobrev
 */
public abstract class Sprite {

  protected int x;
  protected int y;
  protected int width;
  protected int height;
  protected boolean visible;
  protected Image image;

  protected String imageUrl;

  public Sprite(int x, int y, String imageUrl) {
    this.x = x;
    this.y = y;
    this.imageUrl = imageUrl;
    visible = true;
    loadImage();
  }

  private void loadImage() {
    URL url = getClass().getClassLoader().getResource(imageUrl);
    ImageIcon ii = new ImageIcon(url);
    image = ii.getImage();
    width = image.getWidth(null);
    height = image.getHeight(null);
  }

  public Image getImage() {
    return image;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public Rectangle getBounds() {
    return new Rectangle(x, y, width, height);
  }
}