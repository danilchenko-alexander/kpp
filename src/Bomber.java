import java.awt.image.BufferedImage;
public class Bomber extends MovingObject {
  BufferedImage image;
  static String int_to_str_X;
  static String int_to_str_Y;
  static int pos_x;
  static int pos_y;
  static Board st_board;

  public Bomber(int x, int y, BufferedImage image, Board board) {
    super(x, y, ResourceLoader.loadImage("res.png").getSubimage(0, 0, 20, 19), board);
    this.speed = SPEED;
    this.board = board;
    this.image = image;
    this.st_board = board;
  }

  public void update() {
    if(BomberGame.getBomber().isAlive){
    if (xd != 0 || yd != 0) {
      move(xd, yd);
    }
    if (colisionWithEnemy()) {
      isAlive = false;
    }
    if (colisionWithFire()) {
      isAlive = false;
    }
    }
  }

  private boolean BlockAvailable() {
    for (int i = 0; i < board.bombs.size(); i++) {
      if ((x) == board.bombs.get(i).x && (y) == board.bombs.get(i).y)
        return false;
    }
    return true;
  }

  void putBomb() {
    if (BlockAvailable()) {
      Bomb bomb = new Bomb(x, y, image, board, 3);
      board.bombs.add(bomb);
      board.board[x][y].changeState(new BombBlock());
    }
  }

  private boolean colisionWithEnemy() {
    int i;
    for (i = 0; i < board.enemies.size(); i++) {
      if (x == board.enemies.get(i).x && y == board.enemies.get(i).y)      
        return true;
    }
    return false;
  }

  public void reset() {
    this.setX(1);
    this.setY(1);
  }
  
  public static void Save(int xBomb,int yBomb,boolean thisBomb){
    int_to_str_X = Integer.toString(BomberGame.getBomber().getX());
    int_to_str_Y = Integer.toString(BomberGame.getBomber().getY());
    SaveGame.write(BomberGame.getFilename(),"Bomber position:  "+int_to_str_X,int_to_str_Y);
    for (int i = 0; i < st_board.enemies.size(); i++)
    {
      int_to_str_X = Integer.toString(st_board.enemies.get(i).x);
      int_to_str_Y = Integer.toString(st_board.enemies.get(i).y);
      SaveGame.write(BomberGame.getFilename(),"Enemy position:  "+int_to_str_X,int_to_str_Y);
    }
    if(thisBomb){
      int_to_str_X = Integer.toString(xBomb);
      int_to_str_Y = Integer.toString(yBomb);
      SaveGame.write(BomberGame.getFilename(),"Bomb position:  "+int_to_str_X,int_to_str_Y);
    }
  }
  
  public Board getBoard() {
    return this.board;
  }
}