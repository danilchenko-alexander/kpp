import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends MovingObject {
  static final int MAXSPEED = 7;
  int frequency =10;
  Random random;
  int count;
  String int_to_str_X;
  String int_to_str_Y;

  public Enemy(int x, int y, BufferedImage image, Board board) {
    super(x, y, ResourceLoader.loadImage("bot1.png").getSubimage(0, 0, 20, 20), board);
    this.board = board;
    count = 0;
    random = new Random();
    this.dir = LEFT;
    this.count = 0;
    this.speed = 1;
  }

  public void moveOtherwise() {
    changeDirection();
    moveForwad();
  }

  private void changeDirection() {
    int nextDir = random.nextInt(4);
    if (nextDir + 1 == dir) {
      changeDirection();
    } else {
      dir = nextDir + 1;
    }
  }

  private void moveForwad() {// TODO try to use goleft
    if (dir == RIGHT) {
      goRight();
    } else if (dir == LEFT) {
      goLeft();
    } else if (dir == UP) {
      goUp();
    } else if (dir == DOWN) {
      goDown();
    }
    move(xd, yd);
  }

  public void update() {
    if (isAlive) {
      if (colisionWithFire()) {
        isAlive = false;
      } else {
        if (count > frequency) {
          moveForwad();
          count = 0;
        } else {
          count++;
        }
      }
    }
  }
}