package edu.cnm.deepdive.craps.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *<code>Game</code> implements a simple play engine for the main roll(bet) of a game of Craps.
 * Additionally, it can be keep a track of tallies across several rounds of play. Side bets
 * will not be included.
 *
 * @author Alex Rauenzahn &amp; Deep Dive Coding Java + Android Cohort 6
 * @version 1.0
 */
public class Game {

  private int pointValue;
  private State state;
  private Random rng;
  private List<int[]> rolls;
  private long wins;
  private long losses;


  /**
   * Initializes this instance with the a specified source of randomness (rng).
   *
   *
   * @param rng Source of randomness.
   */
  public Game(Random rng) {
    this.rng = rng;
    rolls = new LinkedList<>();
  }

  /**
   *Prepares this instance to play a round&mdash; i.e. returns the game state to {@link State#COME_OUT}
   * clears the established point, and clears the list of rolls. This method <strong>MUST</strong>
   * be invoked prior to {@link #play()}, even immediately after creating and initializing the
   * instance via {@link #Game(Random)}.
   */
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

  /**
   * Plays one round of craps, from come-out roll to win or loss. If this is invoked on an instance
   * in a terminal state{@link State#WIN} or {@link State#LOSS}, no excpetion is thrown. But the
   * state of this <code>Game</code> instance doesn't change.
   * User<strong>MUST</strong> reset before playing game, every time.
   * @return Terminal state of game {@link State#WIN} or {@link State#LOSS} of game.
   */
  public State play() {
    while (state != State.WIN && state != State.LOSS) {
      roll();
      if (state == State.WIN) {
        wins++;
      } else if (state == State.LOSS) {
        losses++;
      }
    }
    return state;
  }

  /**
   * Returns an established point.
   * @return
   */
  public int getPointValue() {
    return pointValue;
  }

  /**
   * Returns current state of game. This will be a terminal {@link State#COME_OUT} state after
   * {@link #reset()} is invoked, and a terminal state {@link State#WIN} or {@link State#LOSS}
   * after {@link #play()} is invoked.
   * @return
   */
  public State getState() {
    return state;
  }

  /**
   * Constructs and returns a Deep copy/safe copy of the list of roll sin the current game.
   * @return
   */
  public List<int[]> getRolls() {
    List<int[]> copy = new LinkedList<>();
    for (int [] roll: rolls){
      copy.add(Arrays.copyOf(roll, roll.length));
    }
    return copy;
  }

  /**
   * Returns the tally of wins recorded since this instance was created
   * and initialized.
   *
   * @return
   */
  public long getWins() {
    return wins;
  }

  /**
   * Returns the tally of losses recorded since this instance was created and initialized.
   *
   * @return
   */
  public long getLosses() {
    return losses;
  }
}
