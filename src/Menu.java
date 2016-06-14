import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Menu extends JFrame {
  public Menu() {
    drawMenuScreen();
  }
  static JFrame frame = new JFrame("bomberman");
  private static JPanel openningPicturePanel;
  private static JLabel openingPictureLabel;
  JFileChooser fileopen = new JFileChooser();
  public KeyboardHandler keyboardHandler;
  JOptionPane jOptionPane = new JOptionPane();
  public Board board = new Board();
  String way = "replays/";
  String fname = null;
  Font font = new Font("SansSerif",Font.ROMAN_BASELINE,11);

  Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
  boolean choice_player = true;
  boolean choice_bot = false;

  private void startComponents(int Level, boolean choice,boolean replay) {
    if (fname == null)
    fname = way + (String)jOptionPane.showInputDialog(null,"¬ведите название игры",
        "¬вод",jOptionPane.INFORMATION_MESSAGE);
    System.out.println(fname);
    BomberGame bomberGame = new BomberGame(Level, choice,fname,replay);
    frame.setLocation(400, 150);
    frame.setLayout(new BorderLayout());
    frame.setSize(bomberGame.getSize());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(bomberGame, BorderLayout.CENTER);
    frame.setVisible(true);
    FrameVisible(true);
    setVisible(false);
  }
  
  public void PanelVisible(boolean b) {
    setVisible(b);
  }

  public static void FrameVisible(boolean b) {
    frame.setVisible(b);
  }
  
  private void drawMenuScreen() {
    openningPicturePanel = new JPanel();
    openningPicturePanel.setLayout(null);
    openingPictureLabel = new JLabel(new ImageIcon("src/menu.jpg"));
    JButton newButton = new JButton("New Game");
    newButton.setSize(120, 25);
    newButton.setLocation(600, 200);
    newButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {
        startComponents(1, choice_player,true);
      }
    });
    openningPicturePanel.add(newButton);

    // botPlayer
    JButton PlayerBotButton = new JButton("PlayerBot");
    PlayerBotButton.setSize(120, 25);
    PlayerBotButton.setLocation(600, 225);
    PlayerBotButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        startComponents(1, choice_bot,true);
      }
    });
    openningPicturePanel.add(PlayerBotButton);

    // slectLevel
    JButton SelectLevel_1 = new JButton("level 1");
    SelectLevel_1.setSize(120, 25);
    SelectLevel_1.setLocation(600, 250);
    SelectLevel_1.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        startComponents(1, choice_player,true);
      }
    });
    openningPicturePanel.add(SelectLevel_1);
    JButton SelectLevel_2 = new JButton("level 2");
    SelectLevel_2.setSize(120, 25);
    SelectLevel_2.setLocation(600, 275);
    SelectLevel_2.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        startComponents(2, choice_player,true);
      }
    });
    openningPicturePanel.add(SelectLevel_2);
    JButton SelectLevel_3 = new JButton("level 3");
    SelectLevel_3.setSize(120, 25);
    SelectLevel_3.setLocation(600, 300);
    SelectLevel_3.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        startComponents(3, choice_player,true);
      }
    });
    openningPicturePanel.add(SelectLevel_3);
    JButton ShowReplay = new JButton ("Show replay");
    ShowReplay.setSize(120,25);
    ShowReplay.setLocation(600,325);
    ShowReplay.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        int ret = fileopen.showDialog(null, "ќткрыть файл");
        if (ret == fileopen.APPROVE_OPTION){
          fname = way+fileopen.getSelectedFile().getName();
          startComponents(1, false,false);
        }
      }
    });
    openningPicturePanel.add(ShowReplay); 
    
    

    JButton ScalaSortButton = new JButton("Scala length");
    ScalaSortButton.setSize(120,25);
    ScalaSortButton.setLocation(600,350);
    ScalaSortButton.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent arg0) {
        String[] files = new String[10000];
        sscs scal = new sscs();
        long startTime = System.currentTimeMillis();
        scal.sort(files);
        long timeSpent = System.currentTimeMillis() - startTime;
        SortTable.ShowTable("scalaSort (критерий - продолжительность игры)= "+timeSpent, files);
      }
    });
    openningPicturePanel.add(ScalaSortButton);
    
    
    JButton ScalaSortButtonBombs = new JButton("Scala bombs");
    ScalaSortButtonBombs.setSize(120,25);
    ScalaSortButtonBombs.setLocation(600,425);
    ScalaSortButtonBombs.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent arg0) {
        String[] files= new String[10000];
        sscs scal = new sscs();
        long startTime = System.currentTimeMillis();
        scal.sortBomb(files);
        long timeSpent = System.currentTimeMillis() - startTime;
        files = FileSorting.getFiles();
        SortTable.ShowTable("scala sort (критерий - бомбы) = "+ timeSpent,files);
      }
    });
    openningPicturePanel.add(ScalaSortButtonBombs);

    JButton ScalaSortButtonBomb = new JButton("Java bombs");
    ScalaSortButtonBomb.setSize(120,25);
    ScalaSortButtonBomb.setLocation(600,400);
    ScalaSortButtonBomb.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent arg0) {
        String[] files;
        long startTime = System.currentTimeMillis();
        FileSorting.bombSort();
        long timeSpent = System.currentTimeMillis() - startTime;
        files = FileSorting.getFiles();
        SortTable.ShowTable("Sort Java (критерий - бомбы) = "+ timeSpent,files);
      }
    });
    openningPicturePanel.add(ScalaSortButtonBomb);
    
    JButton sortTableButton = new JButton("Java length");
    sortTableButton.setSize(120,25);
    sortTableButton.setLocation(600,375);
    sortTableButton.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent arg0) {
        String[] files;
        long startTime = System.currentTimeMillis();
        FileSorting.sort();
        long timeSpent = System.currentTimeMillis() - startTime;
        files = FileSorting.getFiles();
        SortTable.ShowTable("Sort Java  (критерий продолжительность игры)= "+ timeSpent,files);
      }
    });
    openningPicturePanel.add(sortTableButton);
    
    JButton statisticButton = new JButton("Statistic");
    statisticButton.setSize(120,25);
    statisticButton.setLocation(600,450);
    statisticButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        String[] str = new String[3];
        sscs sc = new sscs();
        sc.statistic(str);
        SortTable.ShowTableStatistic("Average Statistic from 5 games", str);
      }
    });
    openningPicturePanel.add(statisticButton);
    
    JButton exitButton = new JButton("Exit");
    exitButton.setSize(120, 25);
    exitButton.setLocation(600, 475);
    exitButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {
        System.exit(0);
      }
    });
    openningPicturePanel.add(exitButton);
    openingPictureLabel.setSize(900, 550);
    openingPictureLabel.setLocation(0, 0);
    openningPicturePanel.add(openingPictureLabel);
    add(openningPicturePanel);
    setSize(900, 550);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);
    setFocusable(true);
  }
}