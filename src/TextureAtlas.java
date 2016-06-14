import java.awt.image.BufferedImage;

public class TextureAtlas {
  static BufferedImage image;
  public TextureAtlas(String imageName) {
    image = ResourceLoader.loadImage(imageName);
  }

  public static BufferedImage cut(int x, int y, int w, int h) {
    return image.getSubimage(x, y, w, h);
  }
}
