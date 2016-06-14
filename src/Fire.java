import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Fire extends GameObject {
  public static final int MAXTIME_FIRE = 7;
  public int range;
  BufferedImage image;
  public int counter;
  public boolean removed;
  sscs scal;
  public Block block;

  public Fire(int x, int y, int range, BufferedImage image, Board board) {
    super(x, y, null, board, false);
    this.range = range;
    this.counter = 0;
    this.removed = false;
    this.board = board;
    scal = new sscs();
    board.getBlockAt(x, y).spreadFireAt(x, y, range);
  }

  @Override
  public boolean burnable() {
    return true;
  }

  @Override
  public boolean solid() {
    return false;
  }

  public void update() {
    if (counter >= MAXTIME_FIRE) {
      scal.writeNotation(44);
      this.removed = true;
      board.getBlockAt(x, y).closeFireAt(x, y, range);
    } else {
      counter++;
    }
  }
  
  public void updateForReplay(){
    if (counter >= 0) {
      this.removed = true;
      board.getBlockAt(x, y).closeFireAt(x, y, range);
    } else {
      counter++;
    }
  }

  public void changeState(BlockState state) {}

  public void draw(Graphics graphics) {}
}