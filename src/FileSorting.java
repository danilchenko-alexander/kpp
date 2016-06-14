import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Random;
import java.util.Scanner;

public class FileSorting {
  static String[] files = new String[10000];
  public static void sort(){
    String s;
    long l;
    int i,j;
    int cycle = 10000;
    long masOfFiles[] = new long [cycle];
    for (i = 0 ; i < cycle; i ++)
    {
      files[i] = "game"+i+".txt";
      s = "replays/game"+i+".txt";
      File f = new File(s);
      masOfFiles[i] = f.length();
    }
    
    for (i = 0; i < cycle-1; i ++){
      for (j = cycle-2 ; j >= i; j--){
        if (masOfFiles[j] > masOfFiles[j+1]){
          l = masOfFiles[j];
          s = files[j];
          masOfFiles[j] = masOfFiles[j+1];
          files[j] = files[j+1];
          masOfFiles[j+1] = l;
          files[j+1] = s;
        }
      }
    }
  }

  
  public static String[] getFiles(){
    return files;
  }
  
  public static void filegeneric() throws IOException{
    Random rand = new Random();
    for (int i =20; i < 10000; i ++){
    File f = new File("replays/game"+i+".txt");
    File file = new File ("replays/game"+rand.nextInt(20)+".txt");
    if(!f.exists())
    f.createNewFile();
    copy(file,f);
    }
    }
  
  public static void bombSort(){
    String s;
    int l;
    long bombs;
    int i,j;
    Scanner sc = null;
    int cycle = 10000;
    int masOfBombs[] = new int [cycle];
    for (i = 0 ; i < cycle; i ++)
    {
      files[i] = "game"+i+".txt";
      s = "replays/game"+i+".txt";
      File f = new File(s);
      try{
        sc = new Scanner(f);
     }catch (FileNotFoundException e){e.printStackTrace();
     }
      while(sc.hasNext()){
        bombs = sc.nextInt();
        if (sc.hasNext()){
        bombs = sc.nextInt();
        if (bombs == 99)
          masOfBombs[i]++;
        }
      }
    }
    
    for (i = 0; i < cycle-1; i ++){
      for (j = cycle-2 ; j >= i; j--){
        if (masOfBombs[j] > masOfBombs[j+1]){
          l = masOfBombs[j];
          s = files[j];
          masOfBombs[j] = masOfBombs[j+1];
          files[j] = files[j+1];
          masOfBombs[j+1] = l;
          files[j+1] = s;
        }
      }
    }
    
  }
  
  public static void copy(File source, File dest)throws IOException{
      FileChannel sourceChannel = new FileInputStream(source).getChannel();
      try{
        FileChannel destChannel = new FileOutputStream(dest).getChannel();
        try
        {
          destChannel.transferFrom(sourceChannel,0, sourceChannel.size());
        }finally{
          destChannel.close();
        }
      }finally{
        sourceChannel.close();
      }
      
    }
  
}
