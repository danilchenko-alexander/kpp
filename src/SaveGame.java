import java.io.FileWriter;
import java.io.IOException;

public class SaveGame{
  public static void write(String fname, String write_X,String write_Y){
      try(FileWriter out = new FileWriter(fname,true)){
        out.append('\n');
        out.write(write_X);
        out.write(" ");
        out.write(write_Y);
        out.write("\r\n");
      }catch(IOException e){
        System.out.println(e.getMessage());
      }
     }
  
  public static void WriteLevel(int level){
    try(FileWriter out = new FileWriter(BomberGame.getFilename(),true)){
      String write_level;
      write_level = Integer.toString(level);
      out.append('\n');
      out.write(write_level);
      out.write("\r\n");
    }catch(IOException e){
      System.out.println(e.getMessage());
    }
  }
  
  public static void WriteStatistic(int bombs,int blockss,int kills){
    try (FileWriter out = new FileWriter(BomberGame.getFilenameWithStatistic(),true)){
      String writenum;
      writenum = Integer.toString(bombs);
      out.append('\n');
      out.write(writenum);
      writenum = Integer.toString(blockss);
      out.write("  ");
      out.write(writenum);
      writenum = Integer.toString(kills);
      out.write("  ");
      out.write(writenum);
    }catch(IOException e){
      System.out.println(e.getMessage());
    }
  }
}