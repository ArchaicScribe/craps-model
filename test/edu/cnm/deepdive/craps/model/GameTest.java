package edu.cnm.deepdive.craps.model;

import static edu.cnm.deepdive.craps.model.State.LOSS;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class GameTest {


  @RepeatedTest(value = 1000)
  void testGameStates() {
    Random rng = new Random();
    Game game = new Game(rng);
    game.reset();
    State state = game.play();
    List<int[]> rolls = game.getRolls();
    assertNotEquals(0, rolls.size(),"A completed game must always have one or more rolls.");
    switch (state) {
      case WIN:
        if (rolls.size() == 1) {
          int [] dice = rolls.get(0);
          int sum = dice[0] + dice[1];
          assertTrue(sum == 7 || sum == 11);
        } else if (rolls.size() > 1) {
          int [] firstRoll = rolls.get(0);
          int [] lastRoll = rolls.get(rolls.size() - 1);
          int firstSum = firstRoll[0] + firstRoll [1];
          int lastSum = lastRoll[0] + lastRoll [1];
          assertEquals(firstSum, lastSum);
      }
        break;
      case LOSS:
        if (rolls.size() == 1){
          int [] dice = rolls.get(0);
          int sum = dice[0] + dice[1];
          assertTrue(sum == 2 || sum == 3 || sum == 12);
        } else if (rolls.size() > 1){
          int [] firstRoll = rolls.get(0);
          int [] lastRoll = rolls.get(rolls.size() - 1);
          int firstSum = firstRoll[0] + firstRoll [1];
          int lastSum = lastRoll[0] + lastRoll [1];
          assertEquals(7, lastSum);
          assertNotEquals(7,firstSum);
        }
        break;
        default:
          fail("A game should not result in any other state besides WIN or LOSS.");
    }
  }

  @Test
  void testTallies() {
    Random rng = new Random();
    Game game = new Game(rng);
    long wins = 0;
    long losses = 0;
    for (int i = 0; i < 1000; i++){
      game.reset();
      State state = game.play();
      if (state == State.WIN){
        wins++;
      } else {
        losses++;
      }
    }
    assertEquals(wins, game.getWins());
    assertEquals(losses, game.getLosses());
  }
}