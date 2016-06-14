import java.awt.image.BufferedImage;

public class SoftBlock extends BlockState {
  BufferedImage image;

  public SoftBlock() {
    image = ResourceLoader.loadImage("map_3.png").getSubimage(0, 0, 20, 20);
  }

  BufferedImage getImage() {
    return image;
  }

  public boolean burnable() {
    // TODO Auto-generated method stub
    return true;
  }

  public boolean solid() {
    // TODO Auto-generated method stub
    return true;
  }
}