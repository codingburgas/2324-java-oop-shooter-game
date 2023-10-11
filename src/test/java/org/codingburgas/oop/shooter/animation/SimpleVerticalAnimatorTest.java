package org.codingburgas.oop.shooter.animation;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SimpleVerticalAnimatorTest {

  @Test
  void getNextPosition() {
    // prepare
    var testee = new SimpleVerticalAnimator(5);
    var initialPosition = new Point(0, -100);

    // act
    var nextPosition = testee.getNextPosition(initialPosition);

    // assert
    assertEquals(initialPosition.y + 5, nextPosition.y);

  }
}