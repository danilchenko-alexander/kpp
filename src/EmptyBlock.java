import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class EmptyBlock extends BlockState {
  public BufferedImage image;

  public EmptyBlock() {
    image = ResourceLoader.loadImage("map_2.png").getSubimage(0, 0, 20, 20);
  }

  BufferedImage getImage() {
    return image;
  }

  @Override
  public boolean solid() {
    return false;// TODO look
  }

  @Override
  public boolean burnable() {
    // TODO Auto-generated method stub
    return false;
  }
  
  public char getItem(){
    return 'n';
  }
}