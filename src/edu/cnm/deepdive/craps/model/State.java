package edu.cnm.deepdive.craps.model;

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



  public State change(int roll, int pointValue){
    return this;

  }

}
