import java.awt.image.BufferedImage;
import java.util.Random;

public class AutomaticHandler extends MovingObject {
  static Random random = new Random();
  int frequency = 5;
  int count = 0;
  static final int LEFT = 1;
  static final int RIGHT = 2;
  static final int UP = 3;
  static final int DOWN = 4;
  static final int BOMB = 5;
  static int di = 3;
  public sscs scal;
  static BufferedImage image;
  static Bomber bomber;

  public AutomaticHandler(int x, int y, Bomber bomber, Board board) {
    super(x, y, image, board);
    this.bomber = bomber;
    this.board = board;
    scal = new sscs();
  }
  
  public void go() {
    di = random.nextInt(5) + 1;
    if(BomberGame.getBomber().isAlive){
    if (di == RIGHT) {
      bomber.goRight();
    } else if (di == LEFT) {
      bomber.goLeft();
    }
    else if (di == UP) {
      bomber.goUp();
    } else if (di == DOWN) {
      bomber.goDown();
    } else if (di == BOMB) {
      bomber.putBomb();
      BomberGame.bombs++;
      scal.writeNotation(99);
    }
    }
  }
  
  public void update() {
    if (count > frequency) {
      go();
      count = 0;
    } else {
      count++;
    }
  }
}
