package Server;

public enum Actions {
  
  PRESS_MOUSE(-1),
  RELEASE_MOUSE(-2),
  PRESS_KEY(-3),
  RELEASE_KEY(-4),
  MOVE_MOUSE(-5);

  private int code;

  Actions(int code){
    this.code = code;
  }

  public int getCode(){
    return code;
  }

}