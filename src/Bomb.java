import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bomb extends GameObject {
  public int range;
  public boolean removed;
  public Board board;
  public static final int MAXTIME = 12;
  public int counter;
  sscs scal;

  public Bomb(int x, int y, BufferedImage image, Board board, int range) {
    super(x, y, null, board, false);
    removed = false;
    this.board = board;
    this.range = range;
    this.counter = 0;
    scal = new sscs();
  }

  @Override
  public boolean burnable() {
    return true;
  }

  @Override
  public boolean solid() {
    return false;
  }

  @Override
  public void draw(Graphics graphics) {
  }

  public void update() {
    if (counter >= MAXTIME) {
      scal.writeNotation(33);
      board.fires.add(new Fire(x, y, this.range, this.image, board));// add fire to board
      this.removed = true;
    } else {
      if (colisionWithFire()) {
        Bomber.Save(33, 33, true);
        board.fires.add(new Fire(x, y, this.range, this.image, board));// add fire to board
        this.removed = true;
      } else {
        counter++;
      }
    }
  }
  
  public void updateForReplay(){
   // if (counter >= 0)
      board.fires.add(new Fire(x, y, this.range, this.image, board));// add fire to board
      this.removed = true;
  }

  private boolean colisionWithFire() {// TODO copied from bomber to upper calss
    if (board.getBlockAt(x, y).state.toString().equals("FireState")) {
      return true;
    }
    return false;
  }

  @Override
  public void changeState(BlockState state) {}
}