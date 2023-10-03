import org.codingburgas.oop.shooter.GameBoard;

import javax.swing.*;
import java.awt.*;

/**
 * Main class for the OOP Shooter game
 * <br>
 * This project is developed for educational purposes only. It is a playground for
 * learning the basic concepts of object-oriented programming with Java.
 *
 * <br>
 * The project is using only standard java libraries. There is
 *
 * @author Martin Dobrev
 */
public class Main extends JFrame {

  public Main() {
    initUI();
  }

  private void initUI() {
    add(new GameBoard());
    setResizable(false);
    pack();

    setTitle("Java OOP - Shooter");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      Main main = new Main();
      main.setVisible(true);
    });
  }
}