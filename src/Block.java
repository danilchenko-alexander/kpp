import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Block extends GameObject {
  public Block(int x, int y, BufferedImage image, Board board) {
    super(x, y, image, board, false);
    this.state = new EmptyBlock();
    this.image = image;
  }

  public void changeState(BlockState state) {
    this.state = state;
  }
  
  public Board getBoard(){
    return board;
  }
  
  public String show(){
    return state.getClass().getName();
  }
  
  @Override
  public boolean burnable() {
    return this.state.burnable();
  }

  @Override
  public boolean solid() {
    return state.solid();
  }

  @Override
  public void draw(Graphics g) {
    g.drawImage(state.getImage(), x * 20, y * 20, 20, 20, null);
  }

  public void changeStateTo(char ch) {
    if (ch == 's') {
      this.changeState(new SoftBlock());
    } else if (ch == 'h') {
      this.changeState(new HardBlock());
    } else if (ch == 'n'){
      this.changeState(new EmptyBlock());
    }
  }

  public void spreadFireAt(int x, int y, int range) {
    this.changeState(new FireState());
    BomberGame.getBomber().update();
    board.getBlockAt(x, y + 1).sendFireDown(range);
    board.getBlockAt(x + 1, y).sendFireRight(range);
    board.getBlockAt(x - 1, y).sendFireLeft(range);
    board.getBlockAt(x, y - 1).sendFireUp(range);
  }

  public void closeFireAt(int x, int y, int range) {
    changeState(new EmptyBlock());
    board.getBlockAt(x, y + 1).sendCloseFireDown(range);
    board.getBlockAt(x + 1, y).sendCloseFireRight(range);
    board.getBlockAt(x - 1, y).sendCloseFireLeft(range);
    board.getBlockAt(x, y - 1).sendCloseFireUp(range);
  }

  private void sendFireUp(int range) {
    if (state.solid()) {
      burnSolid();
    } else {
      changeState(new FireState());
      BomberGame.getBomber().update();
      if (range > 1) {
        board.getBlockAt(x, y - 1).sendFireUp(range - 1);
      }
    }
  }

  private void burnSolid() {
    if (state.burnable()) {
      changeState(new FireState());
      BomberGame.blockss++;
    }
  }

  private void sendFireLeft(int range) {
    if (state.solid()) {
      burnSolid();
    } else {
      changeState(new FireState());
      BomberGame.getBomber().update();
      if (range > 1) {
        board.getBlockAt(x - 1, y).sendFireLeft(range - 1);
      }
    }
  }

  private void sendFireRight(int range) {
    if (state.solid()) {
      burnSolid();
    } else {
      changeState(new FireState());
      BomberGame.getBomber().update();
      if (range > 1) {
        board.getBlockAt(x + 1, y).sendFireRight(range - 1);
      }
    }
  }

  private void sendFireDown(int range) {
    if (state.solid()) {
      burnSolid();
    } else {
      changeState(new FireState());
      BomberGame.getBomber().update();
      if (range > 1) {
        board.getBlockAt(x, y + 1).sendFireDown(range - 1);
      }
    }
  }

  private void sendCloseFireDown(int range) {
    if (!state.solid()) {
      changeState(new EmptyBlock());
      if (range > 1) {
        if (y + 1 < board.BLOCKNUMBER)
          board.getBlockAt(x, y + 1).sendCloseFireDown(range - 1);
      }
    }
  }

  private void sendCloseFireLeft(int range) {
    if (!state.solid()) {
      changeState(new EmptyBlock());
      if (range > 1) {
        if (x - 1 > 0)
          board.getBlockAt(x - 1, y).sendCloseFireLeft(range - 1);
      }
    }
  }

  private void sendCloseFireRight(int range) {
    if (!state.solid()) {
      changeState(new EmptyBlock());
      if (range > 1) {
        if (x + 1 < board.BLOCKNUMBER)
          board.getBlockAt(x + 1, y).sendCloseFireRight(range - 1);
      }
    }
  }

  private void sendCloseFireUp(int range) {
    if (!state.solid()) {
      changeState(new EmptyBlock());
      if (range > 1) {
        if (y - 1 > 0)
          board.getBlockAt(x, y - 1).sendCloseFireUp(range - 1);
      }
    }
  }
}
