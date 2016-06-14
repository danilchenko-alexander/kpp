import java.awt.image.BufferedImage;

public class BombBlock extends BlockState {
  BufferedImage image;

  public BombBlock() {
    this.image = ResourceLoader.loadImage("bomb.png").getSubimage(0, 0, 20, 20);
  }

  BufferedImage getImage() {
    return image;
  }

  @Override
  public boolean solid() {
    return true;
  }

  @Override
  public boolean burnable() {
    // TODO Auto-generated method stub
    return true;
  }
}