import org.codingburgas.oop.shooter.Board;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

  public Main() {
    initUI();
  }

  private void initUI() {
    add(new Board());
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