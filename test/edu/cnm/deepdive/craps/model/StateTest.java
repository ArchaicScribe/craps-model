package edu.cnm.deepdive.craps.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import org.junit.jupiter.api.Test;

class StateTest {

  @Test
  void change() {
    Random rng = new Random();
    assertEquals(State.WIN,State.COME_OUT.change(7, 0));
    assertEquals(State.WIN, State.COME_OUT.change(11, 0));
    assertEquals(State.LOSS, State.COME_OUT.change(2, 0));
    assertEquals(State.LOSS, State.COME_OUT.change(3, 0));
    assertEquals(State.LOSS, State.COME_OUT.change(12, 0));
    int[] comeOutPoint = {4, 5, 6, 8, 9, 10};
    for (int pointValue: comeOutPoint){
      assertEquals(State.POINT, State.COME_OUT.change(pointValue, 0));
      assertEquals(State.WIN, State.POINT.change(pointValue, pointValue));
      assertEquals(State.LOSS, State.POINT.change(7, pointValue));
    }
    for(int i = 2; i <= 12; i++){
      assertEquals(State.WIN, State.WIN.change(i, 0));
      assertEquals(State.LOSS, State.LOSS.change(i, 0));
    }
  }
}