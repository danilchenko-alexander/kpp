import java.awt.image.BufferedImage;

public abstract class BlockState {
  abstract BufferedImage getImage();

  public abstract boolean burnable();

  public abstract boolean solid();
}