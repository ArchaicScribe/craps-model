package edu.cnm.deepdive.craps.model;

/**
 * In this list of ENUMS, we are implementing a basic state machine of a play of the game of
 * Craps. The player starts with the come-out roll, and the ending roll with either a win or loss
 * of the main bet.
 *
 * @author Alex Rauenzahn &amp; Deep Dive Coding Java + Android Cohort 6
 * @version 1.0
 */
public enum State {
  COME_OUT {
    @Override
    public State change(int roll, int pointValue) {
      switch (roll){
        case 2:
        case 3:
        case 12:
          return LOSS;
        case 7:
        case 11:
          return WIN;
        default:
          return POINT;
      }
    }
  },
  POINT {
    @Override
    public State change(int roll, int pointValue) {
      if (roll == 7){
        return LOSS;
      } if (roll == pointValue){
        return WIN;
      }
      return this;
    }
  },
  WIN,
  LOSS;


  /**
   *Applies the specified roll sum to this state, returning the state resulting from the transition
   * represented by the roll. For the terminal states{@link #WIN} and {@link #LOSS}, there will
   * be no change from any roll to the sum value.
   *
   *
   * @param roll    sum of the dice in the current roll.
   * @param pointValue  Established pointValue (ignored unless this state is at {@link #POINT}
   * @return  State resulting from transition.
   */
  public State change(int roll, int pointValue){
    return this;

  }

}
