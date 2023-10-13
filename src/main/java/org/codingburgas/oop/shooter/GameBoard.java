package org.codingburgas.oop.shooter;

import org.codingburgas.oop.shooter.animation.SimpleHorizontalAnimator;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * class GameBoard controls the logic of the game
 * <p>
 *
 * Initialises the game board, all objects, also controls their behaviour.
 * <p>
 * {@link Timer} is used to trigger the frame animation. All objects
 * are rendered as images.
 *
 * @author Martin Dobrev
 */
public class GameBoard extends JPanel implements ActionListener {

  private Timer timer;
  private SpaceShip spaceship;
  private List<Alien> aliens;
  private boolean inGame;
  public static final int INITIAL_SPACESHIP_POSITION_X = 400;
  public static final int INITIAL_SPACESHIP_POSITION_Y = 700;
  public static final int BOARD_WIDTH = 800;
  public static final int BOARD_HEIGHT = 800;
  private final int REDRAW_BOARD_DELAY_MILLIS = 15;

  private final int[][] pos;

  /**
   * GameBoard constructor
   * <p>
   * Creates random positions for the enemies. Initializes the board.
   *
   */
  public GameBoard() {
    final Random r = new Random();
    // int numberEnemies = r.nextInt(30, 60);
    int numberEnemies = r.nextInt(2, 4);
    pos = new int[numberEnemies][2];
    for (int i = 0; i < numberEnemies; i++) {
      int[] pair = new int[]{
          r.nextInt(20, BOARD_WIDTH - 20),
          r.nextInt(-BOARD_HEIGHT, 0)
      };
      pos[i] = pair;
    }
    initBoard();
  }

  /**
   * GameBoard initialization
   * <p>
   * Adds keyboard and mouse listeners. Creates enemies and user spaceship. Starts the timer so that
   * the game can begin.
   */
  private void initBoard() {
    MouseNavigationListener mouseListener = new MouseNavigationListener();
    addKeyListener(new KeyboardNavigationAdapter());
    addMouseListener(mouseListener);
    addMouseMotionListener(mouseListener);
    setFocusable(true);
    setBackground(Color.BLACK);
    inGame = true;

    setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));

    spaceship = new SpaceShip(INITIAL_SPACESHIP_POSITION_X, INITIAL_SPACESHIP_POSITION_Y, 5);

    initAliens();

    timer = new Timer(REDRAW_BOARD_DELAY_MILLIS, this);
    timer.start();
  }

  public void initAliens() {
    aliens = new ArrayList<>();
    aliens.add(new AlienDrone(300, -10));
//    var random = new Random();
//    for (int[] p : pos) {
//      if (random.nextBoolean()) {
//        aliens.add(new AlienDrone(p[0], p[1]));
//      } else {
//        aliens.add(new AlienFighter(p[0], p[1]));
//      }
//    }
  }

  /**
   * Draws the objects on the board
   * <p>
   * This method is overriden from {@link java.awt.Component}. It provides the {@link Graphics} necessary for the
   * drawing of objects on the board.
   * @param g Graphics object for painting on screen
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (inGame) {
      drawObjects(g);
    } else {
      drawGameOver(g);
    }
    Toolkit.getDefaultToolkit().sync();
  }

  /**
   * Draws the objects on scree
   * @param g Graphics object for painting on screen
   */
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

  /**
   * Draws text "Game Over" on screen
   * @param g Graphics object
   */
  private void drawGameOver(Graphics g) {

    String msg = "Game Over";
    Font small = new Font("Helvetica", Font.BOLD, 14);
    FontMetrics fm = getFontMetrics(small);

    g.setColor(Color.white);
    g.setFont(small);
    g.drawString(msg, (BOARD_WIDTH - fm.stringWidth(msg)) / 2,
        BOARD_HEIGHT / 2);
  }

  /**
   * Listener for the frame change event
   * <p>
   * When the game is started, a timer is started in the initBoard method.
   * This timer refreshes the UI after a predefined period of time (REDRAW_BOARD_DELAY_MILLIS)
   *
   * @param actionEvent - refresh board timer event
   */
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    inGame();
    updateShip();
    updateMissiles();
    updateAliens();
    checkCollisions();
    repaint();
  }

  /**
   * Stops the game in case the user spaceship was destroyed (inGame == false)
   */
  private void inGame() {
    if (!inGame) {
      timer.stop();
    }
  }

  /**
   * Moves the user spaceship
   */
  private void updateShip() {
    if (spaceship.isVisible()) {
      spaceship.move();
    }
  }

  /**
   * Update the positions of all missiles
   */
  private void updateMissiles() {
    List<Missile> ms = spaceship.getMissiles();
    List<Missile> missilesToRemove = new ArrayList<>();
    for (Missile m : ms) {
      if (m.isVisible()) {
        m.move();
      } else {
        missilesToRemove.add(m);
      }
    }
    for (Missile missile : missilesToRemove) {
      spaceship.getMissiles().remove(missile);
    }
  }

  /**
   * Updates the positions of the aliens
   * <p>
   * if they are not visible (destroyed), remove them
   */
  private void updateAliens() {
    if (aliens.isEmpty()) {
      inGame = false;
      return;
    }

    List<Alien> aliensToRemove = new ArrayList<>();
    for (Alien a : aliens) {
      if (a.isVisible()) {
        if (a instanceof AlienDrone && a.getY() > 350) {
          AlienDrone alienDrone = (AlienDrone) a;
          if (!(alienDrone.getAnimator() instanceof SimpleHorizontalAnimator)) {
            alienDrone.setAnimator(new SimpleHorizontalAnimator(2));
          }
        }
        a.move();
      } else {
        aliensToRemove.add(a);
      }
    }

    for (Alien alienToRemove: aliensToRemove) {
      aliens.remove(alienToRemove);
    }
  }

  /**
   * Check collisions between objects
   * <p>
   * If aliens collide with the spaceship - game over
   * <p>
   * Each alien that collides with a missile is destroyed
   */
  public void checkCollisions() {
    Rectangle r3 = spaceship.getBounds();
    for (Alien alien : aliens) {
      Rectangle r2 = alien.getBounds();
      if (r3.intersects(r2)) {
        spaceship.setVisible(false);
        alien.setVisible(false);
        inGame = false;
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

  /**
   * Keyboard navigation adapter
   * <p>
   * Triggers spaceship navigation with the keyboard
   * <p>
   * Arrows control the user spaceship, SPACE can be used to fire missiles
   * <p>
   * ESC pauses/continues the game
   */
  private class KeyboardNavigationAdapter extends KeyAdapter {

    @Override
    public void keyReleased(KeyEvent e) {
      spaceship.keyReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        if (timer.isRunning()) {
          timer.stop();
        } else {
          timer.start();
        }
        return;
      }
      spaceship.keyPressed(e);
    }
  }

  /**
   * Mouse navigation listener
   */
  private class MouseNavigationListener implements MouseListener, MouseMotionListener {

    /**
     * Fire on mouse click
     *
     * @param mouseEvent click mouse event
     */
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

    /**
     * Spaceship is moved with the mouse cursor
     *
     * @param mouseEvent move mouse event
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
      spaceship.x = mouseEvent.getX();
      spaceship.y = mouseEvent.getY();
    }
  }
}
