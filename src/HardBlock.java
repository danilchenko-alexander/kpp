import java.awt.image.BufferedImage;

public class HardBlock extends BlockState {
  BufferedImage image;

  public HardBlock() {
    image = ResourceLoader.loadImage("map_1.png").getSubimage(0, 0, 20, 20);
  }

  BufferedImage getImage() {
    return image;
  }

  public boolean solid() {
    return true;
  }
  
  public boolean burnable() {
    return false;
  }
}