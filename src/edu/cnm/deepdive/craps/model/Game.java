package edu.cnm.deepdive.craps.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Game {

  private int pointValue;
  private State state;
  private Random rng;
  private List<int[]> rolls;

  public Game (Random rng) {
    this.rng = rng;
    rolls = new LinkedList<>();
  }

  public void reset() {
    state = State.COME_OUT;
    pointValue = 0;
    rolls.clear();
  }

  private void roll() {
    int die0 = rng.nextInt(6) + 1;
    int die1 = rng.nextInt(6) + 1;
    int sum = die0 + die1;
    State newState = state.change(sum, pointValue);
    if (state == State.COME_OUT && newState == State.POINT) {
      pointValue = sum;
    }
    state = newState;
    int[] diceRoll = {die0, die1};
    rolls.add(diceRoll);
  }

  public State play() {
    while (state != State.WIN && state != State.LOSS) {
      roll();
    }
    return state;
  }

  public int getPointValue() {
    return pointValue;
  }

  public State getState() {
    return state;
  }

  public List<int[]> getRolls() {
    return new LinkedList<int[]>(rolls);
  }

}
