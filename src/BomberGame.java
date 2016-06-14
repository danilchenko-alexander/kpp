import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BomberGame extends JPanel implements Runnable{
  static final int FREQ = 60;
  public static final int LASTLEVEL = 3;
  public static final int WIDTH = 500;
  public static final int HEIGHT = 500;
  public static int bombs =0;
  public static int blockss =0;
  public static int kills =0;
  private int pos_x = 0;
  private int pos_y = 0;
  private int position = 0;
  private int levelNo;
  private Timer gameTimer;
  public static String filename;
  public static String filenameWithStatistic;
  private LevelLoader levelLoader;
  private boolean completed;
  private boolean running;
  private boolean choice;
  private int ups = 0;
  Bomber bomber;
  boolean REPLAY;
  public static Bomber giveBomber;
  public static Board board;
  private KeyboardHandler keyboardHandler;
  AutomaticHandler automaticHandler;
  Thread threadReplay;
  static Scanner sc;
  
  private BufferedImage image;

  public static void main(String[] args) throws IOException {
    Menu menu = new Menu();
  }

  public BomberGame(int Level, boolean choice,String fname,boolean REPLAY) {
    setSize(HEIGHT + 20, WIDTH + 40);
    setVisible(true);
    threadReplay = new Thread(this);
    board = new Board();
    this.choice = choice;
    this.REPLAY = REPLAY;
    this.filename = fname + ".txt";
    this.filenameWithStatistic = fname + "_statistic.txt";
    this.bomber = new Bomber(1, 1, image, board);
    this.giveBomber = bomber;
    this.running = false;
    this.completed = true;
    this.levelNo = Level;
    levelLoader = new LevelLoader(this);
    if (choice && REPLAY) {// Player
      levelLoader.loadLevel(levelNo);
      keyboardHandler = new KeyboardHandler(bomber,board);
      addKeyListener(keyboardHandler);
      SaveGame.WriteLevel(Level);
      running();
    } else if (!choice && REPLAY){ // bot
      levelLoader.loadLevel(levelNo);
      SaveGame.WriteLevel(Level);
     automaticHandler = new AutomaticHandler(1, 1, bomber, board);
     running();
    }
    else if (!choice && !REPLAY){ //replay
     try{
       this.sc = new Scanner(new File(BomberGame.getFilename()));
    }catch (FileNotFoundException e){e.printStackTrace();
    }
     levelNo = sc.nextInt();
     levelLoader.loadLevel(levelNo);
     threadReplay.start();
    }
  }

  private void running() {
    ActionListener listener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        requestFocusInWindow();
        if (running) {
          update();
        } else {
          gameTimer.stop();
          if (completed) {
            if (nextLevelExist()) {
              levelNo++;
              levelLoader.loadLevel(levelNo);
              running();
            } else {
              printVictoryMessage();
              SaveGame.WriteStatistic(bombs,blockss,kills);
              bombs=blockss=kills=0;
            }
          } else {
            printByeMessage();
            SaveGame.WriteStatistic(bombs,blockss,kills);
            bombs=blockss=kills=0;
          }
        }
      }

      private void printVictoryMessage() {
        System.out.println("Yaaaaay you passed all levels");
      }

      private void printByeMessage() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("You lose.");
        frame.add(button);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        button.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
            Menu.FrameVisible(false);
            frame.dispose();
            Menu menu = new Menu();
          }
        });
      }
    };
    gameTimer = new Timer(FREQ, listener);
    gameTimer.start();
  }

  public static Board getBoard(){
    return board;
  }
  
  public boolean nextLevelExist() {
    if (levelNo < LASTLEVEL) {
      return true;
    } else
      return false;
  }

  private void draw() {
    repaint();
  }

  void update() {
    if (ups < 60)
      ups++;
    else
      ups = 0;
    if (bomber.isAlive) {
      if (!board.allEnemiesRemoved) {
        if (!choice){
          automaticHandler.update();
          board.update(REPLAY);
        }
        else {
          board.update(REPLAY);
        }
        draw();
      } else {
        this.running = false;
        this.completed = true;
      }
    } else {
      this.running = false;
      this.completed = false;
    }
  }

  public void addEnemy(Enemy gameObj) {
    board.addEnemy(gameObj);
  }

  public void setBlockAt(int x, int y, Block block) {
    board.setBlockBlockAt(x, y, block);
  }

  public Block getBlockAt(int x, int y) {
    return board.getBlockAt(x, y);
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public void setRunning(boolean running) {
    this.running = running;
  }
  
  public static String getFilename(){
    return filename;
  }
  
  public static String getFilenameWithStatistic(){
    return filenameWithStatistic;
  }
  
  public static Bomber getBomber(){
    return giveBomber;
  }

  public void paint(Graphics graphics) {
    super.paint(graphics);
    board.draw(graphics);
    bomber.draw(graphics);
  }

 
  
  private int getPos(){
    if (sc.hasNext()){
      position = sc.nextInt();
      return position;
    }
    else{
      draw();
      try {
        threadReplay.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return 0;
  }
  
  public synchronized void run() {
    while (threadReplay.isAlive() || bomber.isAlive){
      try {
        threadReplay.sleep(80);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if (pos_x == 0 && pos_y == 0){
        pos_x = getPos();
        pos_y = getPos();
      }
      bomber.setX(pos_x);
      bomber.setY(pos_y);
      for (int i = 0 ; i < board.enemies.size(); i++){
        pos_x = getPos();
        pos_y = getPos();
        board.enemies.get(i).x = pos_x;
        board.enemies.get(i).y = pos_y;
      }
      pos_x = getPos();
      pos_y = getPos();
      if (pos_x == 99 && pos_y == 99)
      {
        bomber.putBomb();
        pos_x = 0;
        pos_y = 0;
      }
      if (pos_x == 33 && pos_y == 33)
      {
        board.updateBombsForReplay();
        pos_x = 0;
        pos_y = 0;
      }
      if (pos_x == 44 && pos_y == 44)
      {
        board.updateFiresForReplay();
        pos_x = 0;
        pos_y = 0;
      }
      board.updateForReplay();
      bomber.update();
      draw();
     }
  }
}