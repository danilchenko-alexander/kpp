import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener {
  Bomber bomber;
  String int_to_str_X;
  String int_to_str_Y;
  Board board;
  sscs scal;
  public KeyboardHandler(Bomber bomber,Board board) {
    this.bomber = bomber;
    this.board = board;
    scal = new sscs();
  }
  
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT){
      bomber.goLeft();
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT){
      bomber.goRight();
    }
    if (e.getKeyCode() == KeyEvent.VK_UP){
      bomber.goUp();
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN){
      bomber.goDown();
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      bomber.putBomb();
      scal.writeNotation(99);
      BomberGame.bombs++;
    }
  }

  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT)
      bomber.stopLeft();
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
      bomber.stopRight();
    if (e.getKeyCode() == KeyEvent.VK_UP)
      bomber.stopUp();
    if (e.getKeyCode() == KeyEvent.VK_DOWN)
      bomber.stopDown();
  }
  
  public void keyTyped(KeyEvent arg0) {
  }
}