import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MovingObject extends GameObject {
  static final int LEFT = 1;
  static final int RIGHT = 2;
  static final int UP = 3;
  static final int DOWN = 4;
  static final int SPEED = 1;
  public int dir;
  public int speed;
  public boolean isAlive;
  public Board board;
  sscs scal;
  public int xd, yd;// horizontal vertical displacements
  public MovingObject(int x, int y, BufferedImage image, Board board) {
    super(x, y, image, board, true);
    this.isAlive = true;
    this.speed = 1;
    this.xd = 0;
    this.yd = 0;
    this.removed = false;
    scal = new sscs();
  }

  public String toString() {
    return null;
  }

  @Override
  public boolean burnable() {
    return true;
  }

  public boolean solid() {
    return false;
  }

  public void draw(Graphics g) {
    g.drawImage(image, x * GameObject.SIZE, y * GameObject.SIZE, null);
  }

  public boolean colisionWithFire() {// TODO copied from bomber to upper calss
    if (board.getBlockAt(x, y).state.toString().equals("FireState")) {
      return true;
    }
    return false;
  }

  protected boolean canWalk() {// TODO direaction.can` yapayim
    if (dir == RIGHT) {
      return !board.board[x + 1][y].solid();
    } else if (dir == DOWN) {
      return !board.board[x][y + 1].solid();
    } else if (dir == UP) {
      return !board.board[x][y - 1].solid();
    } else if (dir == LEFT) {
      if (x - 1 > 0)
        return !board.board[x - 1][y].solid();
    }
    return false;
  }

  public void goRight() {
    yd = 0;
    xd = speed;
  }

  public void goLeft() {
    yd = 0;
    xd = -speed;
  }

  public void goUp() {
    xd = 0;
    yd = -speed;
  }

  public void goDown() {
    xd = 0;
    yd = speed;
  }

  public void stopRight() {
    if (xd > 0) {
      xd = 0;
    }
  }

  public void stopLeft() {
    if (xd < 0) {
      xd = 0;
    }
  }

  public void stopUp() {
    if (yd < 0) {
      yd = 0;
    }
  }

  public void stopDown() {
    if (yd > 0) {
      yd = 0;
    }
  }
  
  public void move(int xd, int yd) {
    if (xd != 0 && yd != 0) {
      move(xd, 0);
      move(0, yd);
      return;
    }

    if (xd > 0)
      dir = RIGHT;
    if (xd < 0)
      dir = LEFT;
    if (yd > 0)
      dir = DOWN;
    if (yd < 0)
      dir = UP;
    // check whether next block walkable
    if (canWalk()) {
      this.setX(this.getX() + xd);
      this.setY(this.getY() + yd);
      //Bomber.Save(1,1,false);
      scal.writeNotation(1);
    } else {
      moveOtherwise();
    }
  }

  public void moveOtherwise() {}
}