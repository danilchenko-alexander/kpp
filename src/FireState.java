import java.awt.image.BufferedImage;

public class FireState extends BlockState {
  BufferedImage image;
  public FireState() {
    this.image = ResourceLoader.loadImage("fire.png").getSubimage(0, 0, 20, 20);
  }

  BufferedImage getImage() {
    return image;
  }

  public boolean solid() {
    return false;
  }

  public boolean burnable() {
    return false;
  }

  public String toString() {
    return "FireState";
  }
}
