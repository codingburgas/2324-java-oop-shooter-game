package org.codingburgas.oop.shooter;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

  private Timer timer;
  private SpaceShip spaceship;
  private List<Alien> aliens;
  private boolean ingame;
  public static final int ICRAFT_X = 400;
  public static final int ICRAFT_Y = 700;
  public static final int B_WIDTH = 800;
  public static final int B_HEIGHT = 800;
  private final int DELAY = 15;

  private final int[][] pos;

  public Board() {
    final Random r = new Random();
    int numberEnemies = r.nextInt(30, 60);
    pos = new int[numberEnemies][2];
    for (int i = 0; i < numberEnemies; i++) {
      int[] pair = new int[]{
          r.nextInt(20, B_WIDTH - 20),
          r.nextInt(-B_HEIGHT, 0)
      };
      pos[i] = pair;
    }
    initBoard();
  }

  private void initBoard() {
    MAdapter mouseListener = new MAdapter();
    addKeyListener(new TAdapter());
    addMouseListener(mouseListener);
    addMouseMotionListener(mouseListener);
    setFocusable(true);
    setBackground(Color.BLACK);
    ingame = true;

    setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

    spaceship = new SpaceShip(ICRAFT_X, ICRAFT_Y);

    initAliens();

    timer = new Timer(DELAY, this);
    timer.start();
    Toolkit tk = getToolkit();
    Cursor transparent = tk.createCustomCursor(tk.getImage(""), new Point(), "trans");
  }

  public void initAliens() {

    aliens = new ArrayList<>();

    for (int[] p : pos) {
      aliens.add(new Alien(p[0], p[1]));
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (ingame) {
      drawObjects(g);
    } else {
      drawGameOver(g);
    }
    Toolkit.getDefaultToolkit().sync();
  }

  private void drawObjects(Graphics g) {

    if (spaceship.isVisible()) {
      g.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(),
          this);
    }

    List<Missile> ms = spaceship.getMissiles();

    for (Missile missile : ms) {
      if (missile.isVisible()) {
        g.drawImage(missile.getImage(), missile.getX(),
            missile.getY(), this);
      }
    }

    for (Alien alien : aliens) {
      if (alien.isVisible()) {
        g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
      }
    }

    g.setColor(Color.WHITE);
    g.drawString("Aliens left: " + aliens.size(), 5, 15);
  }

  private void drawGameOver(Graphics g) {

    String msg = "Game Over";
    Font small = new Font("Helvetica", Font.BOLD, 14);
    FontMetrics fm = getFontMetrics(small);

    g.setColor(Color.white);
    g.setFont(small);
    g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
        B_HEIGHT / 2);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    inGame();
    updateShip();
    updateMissiles();
    updateAliens();
    checkCollisions();
    repaint();
  }

  private void inGame() {
    if (!ingame) {
      timer.stop();
    }
  }

  private void updateShip() {
    if (spaceship.isVisible()) {
      spaceship.move();
    }
  }

  private void updateMissiles() {
    List<Missile> ms = spaceship.getMissiles();
    for (int i = 0; i < ms.size(); i++) {
      Missile m = ms.get(i);
      if (m.isVisible()) {
        m.move();
      } else {
        ms.remove(i);
      }
    }
  }

  private void updateAliens() {
    if (aliens.isEmpty()) {
      ingame = false;
      return;
    }

    for (int i = 0; i < aliens.size(); i++) {
      Alien a = aliens.get(i);
      if (a.isVisible()) {
        a.move();
      } else {
        aliens.remove(i);
      }
    }
  }

  public void checkCollisions() {
    Rectangle r3 = spaceship.getBounds();
    for (Alien alien : aliens) {
      Rectangle r2 = alien.getBounds();
      if (r3.intersects(r2)) {
        spaceship.setVisible(false);
        alien.setVisible(false);
        ingame = false;
      }
    }

    List<Missile> ms = spaceship.getMissiles();
    for (Missile m : ms) {
      Rectangle r1 = m.getBounds();
      for (Alien alien : aliens) {
        Rectangle r2 = alien.getBounds();
        if (r1.intersects(r2)) {
          m.setVisible(false);
          alien.setVisible(false);
        }
      }
    }
  }

  private class TAdapter extends KeyAdapter {

    @Override
    public void keyReleased(KeyEvent e) {
      spaceship.keyReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
      spaceship.keyPressed(e);
    }
  }

  private class MAdapter implements MouseListener, MouseMotionListener {

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
      spaceship.fire();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
      spaceship.x = mouseEvent.getX();
      spaceship.y = mouseEvent.getY();
    }
  }
}
